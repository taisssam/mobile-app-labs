package dev.pikmitrade.domain.usecase

import dev.pikmitrade.domain.model.market.Currency
import dev.pikmitrade.domain.model.trading.Trade
import dev.pikmitrade.domain.model.trading.TradeType
import dev.pikmitrade.domain.model.user.Holding
import dev.pikmitrade.domain.model.user.Portfolio
import dev.pikmitrade.domain.repository.AssetRepository
import dev.pikmitrade.domain.repository.NotificationsRepository
import dev.pikmitrade.domain.repository.PortfolioRepository
import dev.pikmitrade.domain.repository.TradeRepository
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.Instant
import java.util.UUID

class ExecuteTradeUseCase(
    private val assetRepository: AssetRepository,
    private val tradeRepository: TradeRepository,
    private val portfolioRepository: PortfolioRepository,
    private val notificationsRepository: NotificationsRepository,
    private val idProvider: () -> String = { UUID.randomUUID().toString() },
    private val nowProvider: () -> Instant = { Instant.now() }
) {

    operator fun invoke(
        userId: String,
        assetId: String,
        type: TradeType,
        quantity: BigDecimal,
        baseCurrency: Currency = Currency.USD,
    ): Portfolio {
        require(quantity > BigDecimal.ZERO) { "Quantity must be > 0" }

        val currentPortfolio = portfolioRepository.getPortfolio(userId)
            ?: Portfolio(userId = userId, holdings = emptyList(), baseCurrency = baseCurrency)

        validateSellQuantityIfNeeded(
            portfolio = currentPortfolio,
            tradeType = type,
            assetId = assetId,
            quantityToSell = quantity
        )

        val trade = Trade(
            id = idProvider(),
            userId = userId,
            assetId = assetId,
            type = type,
            quantity = quantity,
            price = assetRepository.getQuote(assetId)?.price
                ?: throw IllegalStateException("No quote for asset $assetId"),
            executedAt = nowProvider()
        )

        val updatedPortfolio = updatePortfolioWithTrade(
            portfolio = currentPortfolio,
            trade = trade
        )

        tradeRepository.addTrade(trade)
        portfolioRepository.savePortfolio(userId, updatedPortfolio)
        notificationsRepository.notifyTrade(trade)

        return updatedPortfolio
    }

    private fun validateSellQuantityIfNeeded(
        portfolio: Portfolio,
        tradeType: TradeType,
        assetId: String,
        quantityToSell: BigDecimal
    ) {
        if (tradeType != TradeType.SELL) return

        val existingHolding = portfolio.holdings.firstOrNull { holding -> holding.assetId == assetId }
            ?: throw IllegalArgumentException("No holding for asset $assetId")

        require(quantityToSell <= existingHolding.quantity) {
            "Cannot sell more than holding for asset $assetId"
        }
    }

    private fun updatePortfolioWithTrade(
        portfolio: Portfolio,
        trade: Trade
    ): Portfolio {
        val holdingsByAssetId = portfolio.holdings
            .associateBy(Holding::assetId)
            .toMutableMap()

        when (trade.type) {
            TradeType.BUY -> updateHoldingsForBuy(
                holdingsByAssetId = holdingsByAssetId,
                trade = trade
            )
            TradeType.SELL -> updateHoldingsForSell(
                holdingsByAssetId = holdingsByAssetId,
                trade = trade
            )
        }

        return portfolio.copy(
            holdings = holdingsByAssetId.values.toList()
        )
    }

    private fun updateHoldingsForBuy(
        holdingsByAssetId: MutableMap<String, Holding>,
        trade: Trade
    ) {
        val existingHolding = holdingsByAssetId[trade.assetId]

        val previousQuantity = existingHolding?.quantity ?: BigDecimal.ZERO
        val updatedQuantity = previousQuantity + trade.quantity

        val updatedAveragePrice = if (existingHolding == null || previousQuantity.isZero()) {
            trade.price
        } else {
            calculateWeightedAveragePrice(
                previousAveragePrice = existingHolding.averagePrice,
                previousQuantity = previousQuantity,
                addedTradePrice = trade.price,
                addedTradeQuantity = trade.quantity
            )
        }

        holdingsByAssetId[trade.assetId] = Holding(
            assetId = trade.assetId,
            quantity = updatedQuantity,
            averagePrice = updatedAveragePrice
        )
    }

    private fun updateHoldingsForSell(
        holdingsByAssetId: MutableMap<String, Holding>,
        trade: Trade
    ) {
        val existingHolding = holdingsByAssetId[trade.assetId]
            ?: throw IllegalArgumentException("No holding for asset ${trade.assetId}")

        require(trade.quantity <= existingHolding.quantity) {
            "Cannot sell more than holding for asset ${trade.assetId}"
        }

        val remainingQuantity = existingHolding.quantity - trade.quantity

        if (remainingQuantity.isZero()) {
            holdingsByAssetId.remove(trade.assetId)
        } else {
            holdingsByAssetId[trade.assetId] = existingHolding.copy(quantity = remainingQuantity)
        }
    }

    private fun calculateWeightedAveragePrice(
        previousAveragePrice: BigDecimal,
        previousQuantity: BigDecimal,
        addedTradePrice: BigDecimal,
        addedTradeQuantity: BigDecimal
    ): BigDecimal {
        val totalCost = previousAveragePrice * previousQuantity + addedTradePrice * addedTradeQuantity
        val totalQuantity = previousQuantity + addedTradeQuantity

        return totalCost.divide(totalQuantity, 8, RoundingMode.HALF_UP)
    }

    private fun BigDecimal.isZero(): Boolean = this.compareTo(BigDecimal.ZERO) == 0
}

package dev.pikmitrade.domain.service

import dev.pikmitrade.domain.model.market.Currency
import dev.pikmitrade.domain.model.trading.Trade
import dev.pikmitrade.domain.model.trading.TradeType
import dev.pikmitrade.domain.model.user.Holding
import dev.pikmitrade.domain.model.user.Portfolio
import dev.pikmitrade.domain.repository.NotificationsRepository
import dev.pikmitrade.domain.repository.PortfolioRepository
import dev.pikmitrade.domain.repository.TradeRepository
import java.math.BigDecimal
import java.math.RoundingMode

class TradeService(
    private val tradeRepository: TradeRepository,
    private val portfolioRepository: PortfolioRepository,
    private val notificationsRepository: NotificationsRepository,
) {
    fun execute(trade: Trade, baseCurrency: Currency = Currency.USD): Portfolio {
        val current = portfolioRepository.getPortfolio(trade.userId)
                ?: Portfolio(trade.userId, emptyList(), baseCurrency)

        val holdingsByAsset = current.holdings.associateBy { it.assetId }.toMutableMap()
        val existing = holdingsByAsset[trade.assetId]

        when (trade.type) {
            TradeType.BUY -> {
                val newQuantity = (existing?.quantity ?: BigDecimal.ZERO) + trade.quantity
                val newAveragePrice = if (existing == null || existing.quantity.compareTo(BigDecimal.ZERO) == 0) {
                    trade.price
                } else {
                    val totalCost = existing.averagePrice.multiply(existing.quantity) + trade.price.multiply(trade.quantity)
                    totalCost.divide(newQuantity, RoundingMode.HALF_UP)
                }
                holdingsByAsset[trade.assetId] = Holding(trade.assetId, newQuantity, newAveragePrice)
            }
            TradeType.SELL -> {
                if (existing == null || trade.quantity > existing.quantity) {
                    throw IllegalArgumentException("Cannot sell more than holding for asset ${trade.assetId}")
                }
                val remaining = existing.quantity - trade.quantity
                if (remaining.compareTo(BigDecimal.ZERO) == 0) {
                    holdingsByAsset.remove(trade.assetId)
                } else {
                    holdingsByAsset[trade.assetId] = existing.copy(quantity = remaining)
                }
            }
        }

        val updated = Portfolio(
                userId = current.userId,
                holdings = holdingsByAsset.values.toList(),
                baseCurrency = current.baseCurrency
        )

        tradeRepository.addTrade(trade)
        portfolioRepository.savePortfolio(trade.userId, updated)
        notificationsRepository.notifyTrade(trade)
        return updated
    }
}

package dev.pikmitrade.domain.model.trading

import java.math.BigDecimal
import java.time.Instant

data class Trade(
    val id: String,
    val userId: String,
    val assetId: String,
    val type: TradeType,
    val quantity: BigDecimal,
    val price: BigDecimal,
    val executedAt: Instant,
)

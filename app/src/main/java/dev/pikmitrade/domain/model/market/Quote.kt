package dev.pikmitrade.domain.model.market

import java.math.BigDecimal
import java.time.Instant

data class Quote(
    val id: String,
    val assetId: String,
    val price: BigDecimal,
    val quoteCurrency: Currency,
    val changePercent: BigDecimal?,
    val timestamp: Instant,
)
package dev.pikmitrade.domain.model.market

import java.math.BigDecimal
import java.time.Instant

data class Candle(
    val assetId: String,
    val openTime: Instant,
    val closeTime: Instant,
    val open: BigDecimal,
    val high: BigDecimal,
    val low: BigDecimal,
    val close: BigDecimal,
    val quoteCurrency: Currency,
)

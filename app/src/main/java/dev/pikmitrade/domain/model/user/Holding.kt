package dev.pikmitrade.domain.model.user

import java.math.BigDecimal

data class Holding(
    val assetId: String,
    val quantity: BigDecimal,
    val averagePrice: BigDecimal,
)

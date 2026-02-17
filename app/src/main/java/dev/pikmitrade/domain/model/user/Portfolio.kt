package dev.pikmitrade.domain.model.user

import dev.pikmitrade.domain.model.market.Currency

data class Portfolio(
    val userId: String,
    val holdings: List<Holding>,
    val baseCurrency: Currency,
)
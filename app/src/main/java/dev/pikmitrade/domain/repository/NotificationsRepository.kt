package dev.pikmitrade.domain.repository

import dev.pikmitrade.domain.model.market.Quote
import dev.pikmitrade.domain.model.trading.Trade

interface NotificationsRepository {
    suspend fun notifyTrade(trade: Trade)
    suspend fun notifyPriceChange(quote: Quote)
    suspend fun cancelAll()
}
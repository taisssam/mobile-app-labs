package dev.pikmitrade.domain.repository

import dev.pikmitrade.domain.model.market.Quote
import dev.pikmitrade.domain.model.trading.Trade

interface NotificationsRepository {
    fun notifyTrade(trade: Trade)
    fun notifyPriceChange(quote: Quote)
    fun cancelAll()
}

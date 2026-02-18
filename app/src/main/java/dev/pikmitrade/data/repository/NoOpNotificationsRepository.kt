package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.model.market.Quote
import dev.pikmitrade.domain.model.trading.Trade
import dev.pikmitrade.domain.repository.NotificationsRepository

class NoOpNotificationsRepository : NotificationsRepository {
    override suspend fun notifyTrade(trade: Trade) {}
    override suspend fun notifyPriceChange(quote: Quote) {}
    override suspend fun cancelAll() {}
}
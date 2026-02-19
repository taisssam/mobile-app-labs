package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.model.market.Quote
import dev.pikmitrade.domain.model.trading.Trade
import dev.pikmitrade.domain.repository.NotificationsRepository
import java.util.concurrent.CopyOnWriteArrayList

class InMemoryNotificationsRepository : NotificationsRepository {
    private val trades = CopyOnWriteArrayList<Trade>()
    private val quotes = CopyOnWriteArrayList<Quote>()

    override fun notifyTrade(trade: Trade) {
        trades += trade
    }

    override fun notifyPriceChange(quote: Quote) {
        quotes += quote
    }

    override fun cancelAll() {
        trades.clear()
        quotes.clear()
    }

    fun getTradeNotifications(): List<Trade> = trades.toList()
    fun getPriceNotifications(): List<Quote> = quotes.toList()
}


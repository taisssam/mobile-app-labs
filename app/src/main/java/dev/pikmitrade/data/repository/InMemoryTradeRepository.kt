package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.model.trading.Trade
import dev.pikmitrade.domain.repository.TradeRepository
import java.util.concurrent.CopyOnWriteArrayList

class InMemoryTradeRepository : TradeRepository {
    private val trades = CopyOnWriteArrayList<Trade>()

    override fun addTrade(trade: Trade) {
        trades += trade
    }

    override fun getTrades(): List<Trade> = trades.toList()

    override fun getTradesByUser(userId: String): List<Trade> =
            trades.filter { it.userId == userId }

    override fun clearTrades() {
        trades.clear()
    }
}

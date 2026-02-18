package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.model.trading.Trade
import dev.pikmitrade.domain.repository.TradeRepository
import java.util.concurrent.CopyOnWriteArrayList

class InMemoryTradeRepository : TradeRepository {
    private val trades = CopyOnWriteArrayList<Trade>()

    override suspend fun addTrade(trade: Trade) {
        trades += trade
    }

    override suspend fun getTrades(): List<Trade> = trades.toList()

    override suspend fun getTradesByUser(userId: String): List<Trade> =
        trades.filter { it.toString().contains(userId) }
        
    override suspend fun clearTrades() {
        trades.clear()
    }
}
package dev.pikmitrade.domain.repository

import dev.pikmitrade.domain.model.trading.Trade

interface TradeRepository {
    suspend fun addTrade(trade: Trade)
    suspend fun getTrades(): List<Trade>
    suspend fun getTradesByUser(userId: String): List<Trade>
    suspend fun clearTrades()
}
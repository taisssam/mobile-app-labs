package dev.pikmitrade.domain.repository

import dev.pikmitrade.domain.model.trading.Trade

interface TradeRepository {
    fun addTrade(trade: Trade)
    fun getTrades(): List<Trade>
    fun getTradesByUser(userId: String): List<Trade>
    fun clearTrades()
}

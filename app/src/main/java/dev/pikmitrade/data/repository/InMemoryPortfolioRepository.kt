package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.model.user.Portfolio
import dev.pikmitrade.domain.repository.PortfolioRepository
import java.util.concurrent.ConcurrentHashMap

class InMemoryPortfolioRepository : PortfolioRepository {
    private val portfolios = ConcurrentHashMap<String, Portfolio>()

    override suspend fun getPortfolio(userId: String): Portfolio? = portfolios[userId]
    override suspend fun savePortfolio(userId: String, portfolio: Portfolio) { portfolios[userId] = portfolio }
    override suspend fun clearPortfolio(userId: String) { portfolios.remove(userId) }
}
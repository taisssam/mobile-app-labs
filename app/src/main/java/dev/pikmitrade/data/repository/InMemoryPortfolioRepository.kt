package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.model.user.Portfolio
import dev.pikmitrade.domain.repository.PortfolioRepository

class InMemoryPortfolioRepository : PortfolioRepository {
    private val portfolios = mutableMapOf<String, Portfolio>()

    override fun getPortfolio(userId: String): Portfolio? = portfolios[userId]
    override fun savePortfolio(userId: String, portfolio: Portfolio) {
        portfolios[userId] = portfolio
    }
    override fun clearPortfolio(userId: String) {
        portfolios.remove(userId)
    }
}

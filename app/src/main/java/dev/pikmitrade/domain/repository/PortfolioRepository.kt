package dev.pikmitrade.domain.repository

import dev.pikmitrade.domain.model.user.Portfolio

interface PortfolioRepository {
    fun getPortfolio(userId: String): Portfolio?
    fun savePortfolio(userId: String, portfolio: Portfolio)
    fun clearPortfolio(userId: String)
}

package dev.pikmitrade.domain.repository

import dev.pikmitrade.domain.model.user.Portfolio

interface PortfolioRepository {
    suspend fun getPortfolio(userId: String): Portfolio?
    suspend fun savePortfolio(userId: String, portfolio: Portfolio)
    suspend fun clearPortfolio(userId: String)
}
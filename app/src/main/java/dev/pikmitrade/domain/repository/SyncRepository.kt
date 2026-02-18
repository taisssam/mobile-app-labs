package dev.pikmitrade.domain.repository

interface SyncRepository {
    suspend fun startMarketSync()
    suspend fun stopMarketSync()
    suspend fun runOneShotSync()
}
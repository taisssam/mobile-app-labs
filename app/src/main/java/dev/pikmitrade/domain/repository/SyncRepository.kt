package dev.pikmitrade.domain.repository

interface SyncRepository {
    fun startMarketSync()
    fun stopMarketSync()
    fun runOneShotSync()
}

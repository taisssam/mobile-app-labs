package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.repository.SyncRepository

class NoOpSyncRepository : SyncRepository {
    override suspend fun startMarketSync() {}
    override suspend fun stopMarketSync() {}
    override suspend fun runOneShotSync() {}
}
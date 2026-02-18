package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.repository.SyncRepository

class NoOpSyncRepository : SyncRepository {
    override fun startMarketSync() {}
    override fun stopMarketSync() {}
    override fun runOneShotSync() {}
}

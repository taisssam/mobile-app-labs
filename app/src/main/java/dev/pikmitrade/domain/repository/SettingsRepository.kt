package dev.pikmitrade.domain.repository

import dev.pikmitrade.domain.model.market.AssetType

interface SettingsRepository {
    suspend fun setThemeDark(enabled: Boolean)
    suspend fun isThemeDark(): Boolean

    suspend fun setLanguageCode(code: String)
    suspend fun getLanguageCode(): String

    suspend fun setNotificationsEnabled(enabled: Boolean)
    suspend fun isNotificationsEnabled(): Boolean

    suspend fun setSelectedMarket(assetType: AssetType)
    suspend fun getSelectedMarket(): AssetType?
}
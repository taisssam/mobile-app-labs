package dev.pikmitrade.domain.repository

import dev.pikmitrade.domain.model.market.AssetType

interface SettingsRepository {
    fun setThemeDark(enabled: Boolean)
    fun isThemeDark(): Boolean

    fun setLanguageCode(code: String)
    fun getLanguageCode(): String

    fun setNotificationsEnabled(enabled: Boolean)
    fun isNotificationsEnabled(): Boolean

    fun setSelectedMarket(assetType: AssetType)
    fun getSelectedMarket(): AssetType?
}

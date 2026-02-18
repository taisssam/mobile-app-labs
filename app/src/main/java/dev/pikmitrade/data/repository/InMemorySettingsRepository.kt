package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.model.market.AssetType
import dev.pikmitrade.domain.repository.SettingsRepository

class InMemorySettingsRepository : SettingsRepository {
    private var dark = false
    private var lang = "en"
    private var notifications = true
    private var selected: AssetType? = null

    override suspend fun setThemeDark(enabled: Boolean) { dark = enabled }
    override suspend fun isThemeDark(): Boolean = dark

    override suspend fun setLanguageCode(code: String) { lang = code }
    override suspend fun getLanguageCode(): String = lang

    override suspend fun setNotificationsEnabled(enabled: Boolean) { notifications = enabled }
    override suspend fun isNotificationsEnabled(): Boolean = notifications

    override suspend fun setSelectedMarket(assetType: AssetType) { selected = assetType }
    override suspend fun getSelectedMarket(): AssetType? = selected
}
package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.model.market.AssetType
import dev.pikmitrade.domain.repository.SettingsRepository

class InMemorySettingsRepository : SettingsRepository {
    private var dark = false
    private var lang = "en"
    private var notifications = true
    private var selected: AssetType? = null

    override fun setThemeDark(enabled: Boolean) {
        dark = enabled
    }
    override fun isThemeDark(): Boolean = dark

    override fun setLanguageCode(code: String) {
        lang = code
    }
    override fun getLanguageCode(): String = lang

    override fun setNotificationsEnabled(enabled: Boolean) {
        notifications = enabled
    }
    override fun isNotificationsEnabled(): Boolean = notifications

    override fun setSelectedMarket(assetType: AssetType) {
        selected = assetType
    }
    override fun getSelectedMarket(): AssetType? = selected
}

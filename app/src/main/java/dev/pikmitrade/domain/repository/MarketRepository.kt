package dev.pikmitrade.domain.repository

import dev.pikmitrade.domain.model.market.*

interface MarketRepository {
    fun getAvailableAssetTypes(): List<AssetType>
    fun getAvailableCurrencies(): List<Currency>

    fun getAssets(assetType: AssetType): List<Asset>
    fun refreshAssets(assetType: AssetType): List<Asset>

    fun getQuotes(assetType: AssetType): List<Quote>
    fun refreshQuotes(assetType: AssetType): List<Quote>
}

package dev.pikmitrade.domain.repository

import dev.pikmitrade.domain.model.market.*

interface MarketRepository {
    suspend fun getAvailableAssetTypes(): List<AssetType>
    suspend fun getAvailableCurrencies(): List<Currency>

    suspend fun getAssets(assetType: AssetType): List<Asset>
    suspend fun refreshAssets(assetType: AssetType): List<Asset>

    suspend fun getQuotes(assetType: AssetType): List<Quote>
    suspend fun refreshQuotes(assetType: AssetType): List<Quote>
}
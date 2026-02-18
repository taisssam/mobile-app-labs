package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.model.market.*
import dev.pikmitrade.domain.repository.MarketRepository
import java.util.concurrent.ConcurrentHashMap

class InMemoryMarketRepository(
    private val assetTypes: List<AssetType> = emptyList(),
    private val currencies: List<Currency> = emptyList()
) : MarketRepository {

    private val assetsByType = ConcurrentHashMap<AssetType, MutableList<Asset>>()
    private val quotesByType = ConcurrentHashMap<AssetType, MutableList<Quote>>()

    override suspend fun getAvailableAssetTypes(): List<AssetType> = assetTypes
    override suspend fun getAvailableCurrencies(): List<Currency> = currencies

    override suspend fun getAssets(assetType: AssetType): List<Asset> =
        assetsByType[assetType]?.toList().orEmpty()

    override suspend fun refreshAssets(assetType: AssetType): List<Asset> =
        getAssets(assetType)
        
    override suspend fun getQuotes(assetType: AssetType): List<Quote> =
        quotesByType[assetType]?.toList().orEmpty()

    override suspend fun refreshQuotes(assetType: AssetType): List<Quote> =
        getQuotes(assetType)

    suspend fun seedAssets(assetType: AssetType, items: List<Asset>) {
        assetsByType[assetType] = items.toMutableList()
    }

    suspend fun seedQuotes(assetType: AssetType, items: List<Quote>) {
        quotesByType[assetType] = items.toMutableList()
    }
}
package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.model.market.*
import dev.pikmitrade.domain.repository.MarketRepository

class InMemoryMarketRepository(
        private val assetTypes: List<AssetType> = emptyList(),
        private val currencies: List<Currency> = emptyList()
) : MarketRepository {

    private val assetsByType = mutableMapOf<AssetType, MutableList<Asset>>()
    private val quotesByType = mutableMapOf<AssetType, MutableList<Quote>>()

    override fun getAvailableAssetTypes(): List<AssetType> = assetTypes
    override fun getAvailableCurrencies(): List<Currency> = currencies

    override fun getAssets(assetType: AssetType): List<Asset> =
            assetsByType[assetType]?.toList().orEmpty()

    override fun refreshAssets(assetType: AssetType): List<Asset> = getAssets(assetType)

    override fun getQuotes(assetType: AssetType): List<Quote> =
            quotesByType[assetType]?.toList().orEmpty()

    override fun refreshQuotes(assetType: AssetType): List<Quote> = getQuotes(assetType)

    fun seedAssets(assetType: AssetType, items: List<Asset>) {
        assetsByType[assetType] = items.toMutableList()
    }

    fun seedQuotes(assetType: AssetType, items: List<Quote>) {
        quotesByType[assetType] = items.toMutableList()
    }
}
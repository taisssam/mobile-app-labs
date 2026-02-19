package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.model.market.*
import dev.pikmitrade.domain.repository.AssetRepository

class InMemoryAssetRepository : AssetRepository {

    private val assets = mutableMapOf<String, Asset>()
    private val quotes = mutableMapOf<String, Quote>()
    private val candles = mutableMapOf<String, MutableList<Candle>>()

    private fun key(assetId: String, interval: CandleInterval) = "$assetId|$interval"

    override fun getAsset(assetId: String): Asset? = assets[assetId]
    override fun getQuote(assetId: String): Quote? = quotes[assetId]

    override fun getCandles(assetId: String, interval: CandleInterval): List<Candle> =
            candles[key(assetId, interval)]?.toList().orEmpty()

    override fun refreshAsset(assetId: String): Asset? = getAsset(assetId)
    override fun refreshQuote(assetId: String): Quote? = getQuote(assetId)

    override fun refreshCandles(assetId: String, interval: CandleInterval): List<Candle> =
            getCandles(assetId, interval)

    fun putAsset(assetId: String, asset: Asset) {
        assets[assetId] = asset
    }
    fun putQuote(assetId: String, quote: Quote) {
        quotes[assetId] = quote
    }
    fun putCandles(assetId: String, interval: CandleInterval, list: List<Candle>) {
        candles[key(assetId, interval)] = list.toMutableList()
    }

    fun seedAssets(items: List<Asset>) {
        items.forEach { assets[it.id] = it }
    }

    fun seedQuotes(items: List<Quote>) {
        items.forEach { quotes[it.assetId] = it }
    }

    fun seedCandles(items: List<Candle>, interval: CandleInterval) {
        if (items.isEmpty()) return
        val byAsset = items.groupBy { it.assetId }
        byAsset.forEach { (assetId, list) -> candles[key(assetId, interval)] = list.toMutableList() }
    }

    fun clearAll() {
        assets.clear()
        quotes.clear()
        candles.clear()
    }
}

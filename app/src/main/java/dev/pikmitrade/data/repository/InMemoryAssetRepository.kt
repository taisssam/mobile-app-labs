package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.model.market.*
import dev.pikmitrade.domain.repository.AssetRepository
import java.util.concurrent.ConcurrentHashMap

class InMemoryAssetRepository : AssetRepository {

    private val assets = ConcurrentHashMap<String, Asset>()
    private val quotes = ConcurrentHashMap<String, Quote>()
    private val candles = ConcurrentHashMap<String, MutableList<Candle>>()

    private fun key(assetId: String, interval: CandleInterval) = "$assetId|$interval"

    override suspend fun getAsset(assetId: String): Asset? = assets[assetId]
    override suspend fun getQuote(assetId: String): Quote? = quotes[assetId]

    override suspend fun getCandles(assetId: String, interval: CandleInterval): List<Candle> =
        candles[key(assetId, interval)]?.toList().orEmpty()

    override suspend fun refreshAsset(assetId: String): Asset? = getAsset(assetId)
    override suspend fun refreshQuote(assetId: String): Quote? = getQuote(assetId)

    override suspend fun refreshCandles(assetId: String, interval: CandleInterval): List<Candle> =
        getCandles(assetId, interval)

    suspend fun putAsset(assetId: String, asset: Asset) { assets[assetId] = asset }
    suspend fun putQuote(assetId: String, quote: Quote) { quotes[assetId] = quote }
    suspend fun putCandles(assetId: String, interval: CandleInterval, list: List<Candle>) {
        candles[key(assetId, interval)] = list.toMutableList()
    }
}
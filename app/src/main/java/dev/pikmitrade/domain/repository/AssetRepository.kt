package dev.pikmitrade.domain.repository

import dev.pikmitrade.domain.model.market.*

interface AssetRepository {
    suspend fun getAsset(assetId: String): Asset?
    suspend fun getQuote(assetId: String): Quote?

    suspend fun getCandles(
        assetId: String,
        interval: CandleInterval
    ): List<Candle>

    suspend fun refreshAsset(assetId: String): Asset?
    suspend fun refreshQuote(assetId: String): Quote?
    suspend fun refreshCandles(assetId: String, interval: CandleInterval): List<Candle>
}
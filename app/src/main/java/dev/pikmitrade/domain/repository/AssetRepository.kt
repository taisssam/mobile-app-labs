package dev.pikmitrade.domain.repository

import dev.pikmitrade.domain.model.market.*

interface AssetRepository {
    fun getAsset(assetId: String): Asset?
    fun getQuote(assetId: String): Quote?

    fun getCandles(assetId: String, interval: CandleInterval): List<Candle>

    fun refreshAsset(assetId: String): Asset?
    fun refreshQuote(assetId: String): Quote?
    fun refreshCandles(assetId: String, interval: CandleInterval): List<Candle>
}

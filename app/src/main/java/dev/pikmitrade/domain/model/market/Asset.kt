package dev.pikmitrade.domain.model.market

data class Asset(
    val id: String,
    val code: String,
    val displayName: String,
    val type: AssetType,
)
package data.model

import kotlinx.serialization.Serializable

@Serializable
data class TidesDomainModel(
    val copyright: String,
    val harbor: String,
    val lat: String,
    val lon: String,
    val date: String,
    val data: List<TideItemDomainModel>
)

@Serializable
data class TideItemDomainModel(
    val time: String,
    val high: String,
    val type: String
)

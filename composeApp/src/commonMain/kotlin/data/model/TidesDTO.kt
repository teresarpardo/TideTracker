package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TidesDTO(
    @SerialName("mareas") val tides: TidesItemDTO
)

@Serializable
data class TidesItemDTO(
    @SerialName("copyright") val copyright: String,
    @SerialName("id") val id: String,
    @SerialName("puerto") val harbor: String,
    @SerialName("fecha") val date: String,
    @SerialName("ndatos") val dataSize: String,
    @SerialName("lat") val lat: String,
    @SerialName("lon") val lon: String,
    @SerialName("datos") val data: TideDTO
)

@Serializable
data class TideDTO(
    @SerialName("marea") val tide: ArrayList<TideDataDTO>
)

@Serializable
data class TideDataDTO(
    @SerialName("hora") val time: String,
    @SerialName("altura") val high: String,
    @SerialName("tipo") val type: String
)

package data

import data.model.TideItemDomainModel
import data.model.TidesDTO
import data.model.TidesDomainModel
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import network.Result
import network.errorHandler

interface TidesDataSource {
    suspend fun getTides(): Result<TidesDomainModel>
}

class TidesDataSourceImpl : TidesDataSource {
    override suspend fun getTides(): Result<TidesDomainModel> {
        return try {
            val response = TidesApi().getTides()
            Result.Success(mapResponse(response))
        } catch (e: Exception) {
            Result.Failure(e.errorHandler())
        }
    }

    private suspend fun mapResponse(response: HttpResponse): TidesDomainModel {
        return try {
            val json = Json { ignoreUnknownKeys = true }
            val jsonElement = json.decodeFromString<TidesDTO>(response.bodyAsText())

            TidesDomainModel(
                copyright = jsonElement.tides.copyright,
                harbor = jsonElement.tides.harbor,
                lat = jsonElement.tides.lat,
                lon = jsonElement.tides.lon,
                date = jsonElement.tides.date,
                data = jsonElement.tides.data.tide.map { tide ->
                    TideItemDomainModel(
                        time = tide.time,
                        high = tide.high,
                        type = tide.type
                    )
                }
            )
        } catch (e: Exception) {
            println("Error parsing response: ${e.message}")
            throw e
        }
    }
}

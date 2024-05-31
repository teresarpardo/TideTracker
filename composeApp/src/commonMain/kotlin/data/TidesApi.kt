package data

import io.ktor.client.request.get
import network.KtorClient

class TidesApi {
    private companion object {
        const val PATH = "https://ideihm.covam.es/api-ihm/getmarea?"
    }

    suspend fun getTides() = KtorClient().client().get(PATH) {
        url {
            parameters.append("request", "gettide")
            parameters.append("id", "11")
            parameters.append("format", "json")
            parameters.append("date", "20240531")
        }
    }
}

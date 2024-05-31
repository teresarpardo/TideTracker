package network

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.errors.IOException

fun Exception.errorHandler() = when (this) {
    is ServerResponseException -> Failure.InternalError
    is ClientRequestException ->
        when (this.response.status) {
            HttpStatusCode.BadRequest -> Failure.BadRequest
            HttpStatusCode.Unauthorized -> Failure.Unauthorized
            HttpStatusCode.Forbidden -> Failure.Forbidden
            HttpStatusCode.NotFound -> Failure.NotFound
            else -> Failure.HttpError(this)
        }

    is RedirectResponseException -> Failure.HttpError(this)
    is IOException -> Failure.NetworkError(this)
    else -> Failure.GenericError(this)
}

sealed class Failure(val cause: String? = null) {
    data object InternalError : Failure(InternalError::class.simpleName)
    data object BadRequest : Failure(BadRequest::class.simpleName)
    data object Unauthorized : Failure(Unauthorized::class.simpleName)
    data object Forbidden : Failure(Forbidden::class.simpleName)
    data object NotFound : Failure(NotFound::class.simpleName)

    class HttpError(e: Exception) : Failure(e.cause?.message)
    class NetworkError(e: Exception) : Failure(e.cause?.message)
    class GenericError(e: Exception) : Failure(e.cause?.message)
}

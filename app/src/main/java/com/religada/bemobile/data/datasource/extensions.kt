package com.religada.bemobile.data.datasource

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.religada.bemobile.domain.ErrorApp
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toError(): ErrorApp = when (this) {
    is IOException -> ErrorApp.Connectivity
    is HttpException -> ErrorApp.Server(code())
    else -> ErrorApp.Unknown(message ?: "")
}

suspend fun <T> tryCall(action: suspend () -> T): Either<ErrorApp, T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}
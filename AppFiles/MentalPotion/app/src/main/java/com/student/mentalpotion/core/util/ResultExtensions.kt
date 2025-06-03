package com.student.mentalpotion.core.util

import com.student.mentalpotion.features.authentication.data.mapper.toNetworkError

suspend fun <T> runFirebaseCatching(block: suspend () -> T): Result<T, NetworkError> =
    try {
        Result.Success(block())
    } catch (e: Throwable) {
        Result.Error(e.toNetworkError())
    }
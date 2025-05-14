package com.student.mpbackoffice.features.authentication.data.mapper

import com.student.mpbackoffice.core.util.ApiError
import com.student.mpbackoffice.core.util.NetworkError
import kotlinx.io.IOException

fun Throwable.toNetworkError(): NetworkError {
    val error = when(this){
        is IOException -> ApiError.NetworkError
        //is HttpException -> ApiError.UnknownResponse
        else -> ApiError.UnknownError
    }
    return NetworkError(
        error = error,
        t = this
    )
}
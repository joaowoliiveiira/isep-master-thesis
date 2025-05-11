package com.student.mentalpotion.features.authentication.data.mapper

import com.student.mentalpotion.core.util.ApiError
import com.student.mentalpotion.core.util.NetworkError
import java.io.IOException

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
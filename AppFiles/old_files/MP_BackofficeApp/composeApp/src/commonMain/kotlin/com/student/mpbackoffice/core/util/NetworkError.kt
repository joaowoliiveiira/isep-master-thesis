package com.student.mpbackoffice.core.util

data class NetworkError (
    val error: ApiError,
    val t: Throwable
)

enum class ApiError(val message: String){
    NetworkError("Network Error"),
    UnknownResponse("Unknown Response"),
    UnknownError("Unknown Error")
}
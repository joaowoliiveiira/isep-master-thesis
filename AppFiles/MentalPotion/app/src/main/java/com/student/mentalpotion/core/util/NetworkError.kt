package com.student.mentalpotion.core.util

data class NetworkError (
    val error: ApiError,
    val t: Throwable
) : Error {
    override val message: String get() = error.message
}

enum class ApiError(val message: String){
    NetworkError("Network Error"),
    UnknownResponse("Unknown Response"),
    UnknownError("Unknown Error")
}
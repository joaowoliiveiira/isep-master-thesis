package com.student.mentalpotion.features.authentication.domain.repository

import arrow.core.Either
import com.student.mentalpotion.features.authentication.domain.model.NetworkError
import com.student.mentalpotion.features.authentication.domain.model.User

interface AuthenticationRepository {
    suspend fun login(email: String, password: String): Either<NetworkError, User>
    suspend fun register(email: String, password: String): Either<NetworkError, User>
    fun logout()
    fun getCurrentUser(): Either<Throwable, User>
    suspend fun resetPassword(email: String): Either<NetworkError, Unit>
}
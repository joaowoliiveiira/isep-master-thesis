package com.student.mpbackoffice.features.authentication.domain.repository

import arrow.core.Either
import com.student.mpbackoffice.core.util.NetworkError
import com.student.mpbackoffice.features.authentication.domain.model.Admin

interface AuthRepository {
    suspend fun login(email: String, password: String): Either<NetworkError, Admin>
    suspend fun register(email: String, password: String): Either<NetworkError, Admin>
    fun logout()
    fun getCurrentUser(): Either<NetworkError, Admin>
    suspend fun resetPassword(email: String): Either<NetworkError, Unit>
}
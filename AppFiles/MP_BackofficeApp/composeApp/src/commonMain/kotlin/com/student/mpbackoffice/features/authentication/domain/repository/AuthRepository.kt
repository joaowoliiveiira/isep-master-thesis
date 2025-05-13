package com.student.mpbackoffice.features.authentication.domain.repository

import arrow.core.Either
import com.student.mpbackoffice.core.util.NetworkError
import com.student.mpbackoffice.features.authentication.domain.model.Admin
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun observeSessionStatus(): Flow<SessionStatus>
    suspend fun login(email: String, password: String): Either<NetworkError, Unit>
    suspend fun logout()
    suspend fun signUp(email: String, password: String): Either<NetworkError, Unit>
    suspend fun resetPassword(email: String)
    suspend fun changePassword(newPassword: String)
}
package com.student.mpbackoffice.features.authentication.data.repository

import arrow.core.Either
import com.student.mpbackoffice.core.util.NetworkError
import com.student.mpbackoffice.features.authentication.data.service.AuthApiInterface
import com.student.mpbackoffice.features.authentication.domain.model.Admin
import com.student.mpbackoffice.features.authentication.domain.repository.AuthRepository
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val authApi: AuthApiInterface
) : AuthRepository {
    override fun observeSessionStatus(): Flow<SessionStatus> {
        TODO("Not yet implemented")
    }

    override suspend fun login(
        email: String,
        password: String
    ): Either<NetworkError, Unit> {
        return authApi.signIn(email, password)
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(
        email: String,
        password: String
    ): Either<NetworkError, Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword(email: String) {
        TODO("Not yet implemented")
    }

    override suspend fun changePassword(newPassword: String) {
        TODO("Not yet implemented")
    }


}

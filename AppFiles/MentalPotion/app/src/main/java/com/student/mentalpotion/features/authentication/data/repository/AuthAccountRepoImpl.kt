package com.student.mentalpotion.features.authentication.data.repository

import com.student.mentalpotion.core.util.Result
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.features.authentication.domain.repository.FirebaseAuthService
import com.student.mentalpotion.features.authentication.domain.model.AuthUser
import com.student.mentalpotion.features.authentication.domain.repository.AuthAccountRepository

class AuthAccountRepoImpl(
    private val authService: FirebaseAuthService
) : AuthAccountRepository {

    override suspend fun login(email: String, password: String): Result<AuthUser, NetworkError> {
        return authService.login(email, password)
    }

    override suspend fun register(email: String, password: String): Result<AuthUser, NetworkError> {
        return authService.register(email, password)
    }

    override fun logout() {
        authService.logout()
    }

    override suspend fun resetPassword(email: String): Result<Unit, NetworkError> {
        return authService.resetPassword(email)
    }
}

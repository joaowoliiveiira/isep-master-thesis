package com.student.mentalpotion.features.authentication.data.repository

import arrow.core.Either
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.features.authentication.domain.model.User
import com.student.mentalpotion.features.authentication.domain.repository.AuthenticationRepository
import com.student.mentalpotion.features.authentication.data.service.FirebaseAuthService

class AuthenticationRepoImpl (
    private val authService: FirebaseAuthService
    ) : AuthenticationRepository {

    override suspend fun login(email: String, password: String): Either<NetworkError, User> {
        return authService.login(email, password)
    }

    override suspend fun register(email: String, password: String): Either<NetworkError, User> {
        return authService.register(email, password)
    }

    override fun logout() {
        authService.logout()
    }

    override fun getCurrentUser(): Either<Throwable, User> {
        return authService.getCurrentUser()
    }

    override suspend fun resetPassword(email: String): Either<NetworkError, Unit> {
        return authService.resetPassword(email)
    }
}
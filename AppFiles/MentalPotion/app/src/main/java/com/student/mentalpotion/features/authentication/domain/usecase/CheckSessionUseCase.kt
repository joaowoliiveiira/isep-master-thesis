package com.student.mentalpotion.features.authentication.domain.usecase

import com.student.mentalpotion.core.util.ApiError
import com.student.mentalpotion.core.util.Result
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.features.authentication.domain.model.AuthUser
import com.student.mentalpotion.features.authentication.domain.repository.AuthAccountRepository

class CheckSessionUseCase(
    private val repository: AuthAccountRepository
) {
    suspend operator fun invoke(): Result<AuthUser, NetworkError> {
        return if (repository.isLoggedIn()) {
            repository.getCurrentUser()
        } else {
            Result.Error(NetworkError(ApiError.UnknownError, Throwable("User not logged in")))
        }
    }
}
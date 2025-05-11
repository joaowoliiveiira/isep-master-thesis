package com.student.mentalpotion.features.authentication.domain.usecase

import arrow.core.Either
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.features.authentication.domain.repository.AuthenticationRepository

class ResetPasswordUseCase(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String): Either<NetworkError, Unit> {
        return repository.resetPassword(email)
    }
}

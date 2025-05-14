package com.student.mpbackoffice.features.authentication.domain.usecase

import arrow.core.Either
import com.student.mpbackoffice.core.util.NetworkError
import com.student.mpbackoffice.features.authentication.domain.repository.AuthRepository

class ResetPasswordUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String): Either<NetworkError, Unit> {
        return repository.resetPassword(email)
    }
}
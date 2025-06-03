package com.student.mentalpotion.features.authentication.domain.usecase

import arrow.core.Either
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.core.util.Result
import com.student.mentalpotion.features.authentication.domain.repository.AuthAccountRepository

class ResetPasswordUseCase(
    private val repository: AuthAccountRepository
) {
    suspend operator fun invoke(email: String): Result<Unit, NetworkError> {
        return repository.resetPassword(email)
    }
}

package com.student.mpbackoffice.features.authentication.domain.usecase

import arrow.core.Either
import com.student.mpbackoffice.core.util.NetworkError
import com.student.mpbackoffice.features.authentication.domain.model.Admin
import com.student.mpbackoffice.features.authentication.domain.repository.AuthRepository
import kotlinx.coroutines.CancellationException

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return try {
            repository.login(email, password)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e // always rethrow cancellation exceptions
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
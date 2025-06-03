package com.student.mentalpotion.features.authentication.domain.usecase

import com.student.mentalpotion.core.util.Result
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.features.authentication.domain.model.AuthUser
import com.student.mentalpotion.features.authentication.domain.repository.AuthAccountRepository

class LoginUseCase(
    private val repository: AuthAccountRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<AuthUser, NetworkError> {
        return repository.login(email, password)
    }
}

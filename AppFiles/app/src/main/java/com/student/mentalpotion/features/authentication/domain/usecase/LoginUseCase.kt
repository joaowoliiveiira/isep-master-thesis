package com.student.mentalpotion.features.authentication.domain.usecase

import com.student.mentalpotion.features.authentication.domain.model.User
import com.student.mentalpotion.features.authentication.domain.repository.AuthenticationRepository

class LoginUseCase(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return repository.login(email, password)
    }
}

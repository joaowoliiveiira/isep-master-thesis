package com.student.mentalpotion.features.authentication.domain.usecase

import arrow.core.Either
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.features.authentication.domain.model.User
import com.student.mentalpotion.features.authentication.domain.repository.AuthenticationRepository

class LoginUseCase(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String, password: String): Either<NetworkError, User> {
        return repository.login(email, password)
    }
}

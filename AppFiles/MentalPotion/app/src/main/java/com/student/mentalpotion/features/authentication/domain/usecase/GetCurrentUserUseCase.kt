package com.student.mentalpotion.features.authentication.domain.usecase

import arrow.core.Either
import com.student.mentalpotion.features.authentication.domain.model.User
import com.student.mentalpotion.features.authentication.domain.repository.AuthenticationRepository

class GetCurrentUserUseCase(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(): Either<Throwable, User> {
        return repository.getCurrentUser()
    }
}
package com.student.mentalpotion.features.authentication.domain.usecase

import com.student.mentalpotion.features.authentication.domain.repository.AuthenticationRepository

class LogoutUseCase(
    private val repository: AuthenticationRepository
) {
    operator fun invoke() {
        repository.logout()
    }
}
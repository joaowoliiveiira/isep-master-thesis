package com.student.mentalpotion.features.authentication.domain.usecase

import com.student.mentalpotion.features.authentication.domain.repository.AuthAccountRepository

class LogoutUseCase(
    private val repository: AuthAccountRepository
) {
    operator fun invoke() {
        repository.logout()
    }
}
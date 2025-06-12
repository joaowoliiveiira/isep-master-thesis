package com.student.mentalpotion.features.authentication.domain.usecase

import com.student.mentalpotion.features.authentication.domain.repository.AuthAccountRepository

class LogoutUseCase(
    private val authRepository: AuthAccountRepository
) {
    suspend operator fun invoke() {
        authRepository.logout()
    }
}
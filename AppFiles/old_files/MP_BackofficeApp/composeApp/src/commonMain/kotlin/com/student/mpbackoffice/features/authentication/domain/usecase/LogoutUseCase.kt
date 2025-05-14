package com.student.mpbackoffice.features.authentication.domain.usecase

import com.student.mpbackoffice.features.authentication.domain.repository.AuthRepository

class LogoutUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke() {
        repository.logout()
    }
}
package com.student.mpbackoffice.features.authentication.domain.usecase

import com.student.mpbackoffice.features.authentication.domain.model.Admin
import com.student.mpbackoffice.features.authentication.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Admin> {
        return repository.login(email, password)
    }
}
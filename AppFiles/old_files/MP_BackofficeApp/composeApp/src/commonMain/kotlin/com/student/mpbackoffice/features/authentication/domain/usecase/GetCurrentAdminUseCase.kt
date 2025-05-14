package com.student.mpbackoffice.features.authentication.domain.usecase

import arrow.core.Either
import com.student.mpbackoffice.core.util.NetworkError
import com.student.mpbackoffice.features.authentication.domain.model.Admin
import com.student.mpbackoffice.features.authentication.domain.repository.AuthRepository

class GetCurrentAdminUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(): Either<NetworkError, Admin> {
        return repository.getCurrentUser()
    }
}
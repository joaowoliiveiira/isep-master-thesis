package com.student.mpbackoffice.features.authentication.domain.usecase

class GetCurrentUserUseCase(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(): Either<Throwable, User> {
        return repository.getCurrentUser()
    }
}
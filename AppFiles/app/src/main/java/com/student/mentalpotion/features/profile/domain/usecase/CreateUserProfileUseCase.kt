package com.student.mentalpotion.features.profile.domain.usecase

import arrow.core.Either
import com.student.mentalpotion.features.authentication.domain.model.NetworkError
import com.student.mentalpotion.features.profile.domain.model.UserProfile
import com.student.mentalpotion.features.profile.domain.repository.UserProfileRepository

class CreateUserProfileUseCase(
    private val repository: UserProfileRepository
) {
    suspend operator fun invoke(profile: UserProfile): Either<NetworkError, Unit> {
        return repository.createUserProfile(profile)
    }
}
package com.student.mentalpotion.features.profile.domain.usecase

import arrow.core.Either
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.features.profile.domain.model.UserProfile
import com.student.mentalpotion.features.profile.domain.repository.UserProfileRepository

class GetUserProfileUseCase(
    private val repository: UserProfileRepository
) {
    suspend operator fun invoke(uid: String): Either<NetworkError, UserProfile> {
        return repository.getUserProfile(uid)
    }
}
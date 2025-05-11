package com.student.mentalpotion.features.profile.data.repository

import arrow.core.Either
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.features.profile.data.service.FirestoreUserProfileService
import com.student.mentalpotion.features.profile.domain.model.UserProfile
import com.student.mentalpotion.features.profile.domain.repository.UserProfileRepository

class UserProfileRepoImpl(
    private val service: FirestoreUserProfileService
) : UserProfileRepository {

    override suspend fun getUserProfile(uid: String): Either<NetworkError, UserProfile> {
        return service.getUserProfile(uid)
    }

    override suspend fun updateUserProfile(userProfile: UserProfile): Either<NetworkError, UserProfile> {
        return service.updateUserProfile(userProfile)
    }

    override suspend fun createUserProfile(userProfile: UserProfile): Either<NetworkError, UserProfile> {
        return service.createUserProfile(userProfile)
    }
}
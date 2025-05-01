package com.student.mentalpotion.features.profile.data.service

import arrow.core.Either
import com.student.mentalpotion.features.authentication.domain.model.NetworkError
import com.student.mentalpotion.features.profile.data.mapper.UserProfileDTO
import com.student.mentalpotion.features.profile.domain.model.UserProfile

interface FirestoreUserProfileService {
    suspend fun createUserProfile(profile: UserProfile): Either<NetworkError, UserProfile>
    suspend fun getUserProfile(uid: String): Either<NetworkError, UserProfile>
    suspend fun updateUserProfile(profile: UserProfile): Either<NetworkError, UserProfile>
}
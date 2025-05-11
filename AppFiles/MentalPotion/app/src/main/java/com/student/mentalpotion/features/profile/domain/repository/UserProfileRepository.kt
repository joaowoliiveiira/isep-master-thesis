package com.student.mentalpotion.features.profile.domain.repository

import arrow.core.Either
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.features.profile.domain.model.UserProfile

interface UserProfileRepository {
    suspend fun getUserProfile(uid: String): Either<NetworkError, UserProfile>
    suspend fun updateUserProfile(userProfile: UserProfile): Either<NetworkError, UserProfile>
    suspend fun createUserProfile(userProfile: UserProfile): Either<NetworkError, UserProfile>
}
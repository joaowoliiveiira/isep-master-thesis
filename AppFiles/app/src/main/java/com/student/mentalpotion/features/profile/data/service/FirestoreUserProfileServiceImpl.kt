package com.student.mentalpotion.features.profile.data.service

import arrow.core.Either
import com.google.firebase.firestore.FirebaseFirestore
import com.student.mentalpotion.features.authentication.data.mapper.toNetworkError
import com.student.mentalpotion.features.authentication.domain.model.NetworkError
import com.student.mentalpotion.features.profile.data.mapper.UserProfileDTO
import com.student.mentalpotion.features.profile.domain.model.UserProfile
import kotlinx.coroutines.tasks.await

class FirestoreUserProfileServiceImpl (
    private val firestore: FirebaseFirestore
) : FirestoreUserProfileService {

    private val usersCollection = firestore.collection("users")

    override suspend fun createUserProfile(profile: UserProfile): Either<NetworkError, UserProfile> {
        return Either.catch {
            val dto = UserProfileDTO.fromDomain(profile)
            firestore.collection("userProfiles")
                .document(profile.uid)
                .set(dto)
                .await()
            profile
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getUserProfile(uid: String): Either<NetworkError, UserProfile> {
        return Either.catch {
            val snapshot = firestore.collection("userProfiles")
                .document(uid)
                .get()
                .await()

            val dto = snapshot.toObject(UserProfileDTO::class.java)
                ?: throw IllegalStateException("User profile not found")

            dto.toDomain()
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun updateUserProfile(profile: UserProfile): Either<NetworkError, UserProfile> {
        return Either.catch {
            val dto = UserProfileDTO.fromDomain(profile)
            firestore.collection("userProfiles")
                .document(profile.uid)
                .set(dto)
                .await()
            profile
        }.mapLeft { it.toNetworkError() }
    }
}
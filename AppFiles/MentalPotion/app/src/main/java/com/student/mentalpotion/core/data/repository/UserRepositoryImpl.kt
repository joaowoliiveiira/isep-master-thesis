package com.student.mentalpotion.core.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.student.mentalpotion.core.domain.model.ProfileUser
import com.student.mentalpotion.core.domain.repository.UserRepository
import com.student.mentalpotion.core.util.ApiError
import com.student.mentalpotion.core.util.Result
import com.student.mentalpotion.core.util.NetworkError
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class UserRepositoryImpl(
    private val firestore: FirebaseFirestore
) : UserRepository {

    override suspend fun createUser(user: ProfileUser): Result<Unit, NetworkError> {
        return try {
            firestore.collection("users")
                .document(user.uid)
                .set(user.toMap())
                .await()

            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(NetworkError(ApiError.UnknownError, e))
        }
    }

    override suspend fun getCurrentUserProfile(): Result<ProfileUser, NetworkError> {
        // Implementation to be added later
        TODO("Not implemented yet")
    }
}

fun ProfileUser.toMap(): Map<String, Any?> = mapOf(
    "uid" to uid,
    "name" to name,
    "email" to email,
    "avatar_id" to avatarId,
    "created_at" to createdAt
)
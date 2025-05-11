package com.student.mentalpotion.features.activities.data.service

import arrow.core.Either
import com.google.firebase.firestore.FirebaseFirestore
import com.student.mentalpotion.features.activities.data.mapper.ActivityDTO
import com.student.mentalpotion.features.activities.domain.model.Activity
import com.student.mentalpotion.features.authentication.data.mapper.toNetworkError
import com.student.mentalpotion.core.util.NetworkError
import kotlinx.coroutines.tasks.await

class FirestoreActivityServiceImpl (
    private val firestore: FirebaseFirestore
) : FirestoreActivityService {

    private val activityCollection = firestore.collection("activities")

    override suspend fun getActivitiesByTopic(topicId: String): Either<NetworkError, List<Activity>> {
        return Either.catch {
            val snapshot = firestore.collection("activities")
                .whereEqualTo("topicId", topicId)
                .get()
                .await()

            snapshot.documents.mapNotNull { doc ->
                doc.toObject(ActivityDTO::class.java)?.toDomain()
            }
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getActivityById(uid: String): Either<NetworkError, Activity> {
        return Either.catch {
            val snapshot = firestore.collection("activities")
                .document(uid)
                .get()
                .await()

            val dto = snapshot.toObject(ActivityDTO::class.java)
                ?: throw NoSuchElementException("Activity with id '$uid' not found")

            dto.toDomain()
        }.mapLeft { it.toNetworkError() }
    }
}
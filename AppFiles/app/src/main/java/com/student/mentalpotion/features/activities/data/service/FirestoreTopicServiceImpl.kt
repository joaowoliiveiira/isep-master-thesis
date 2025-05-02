package com.student.mentalpotion.features.activities.data.service

import arrow.core.Either
import arrow.core.raise.either
import com.google.firebase.firestore.FirebaseFirestore
import com.student.mentalpotion.features.activities.data.mapper.TopicDTO
import com.student.mentalpotion.features.activities.domain.model.Topic
import com.student.mentalpotion.features.authentication.data.mapper.toNetworkError
import com.student.mentalpotion.features.authentication.domain.model.NetworkError
import com.student.mentalpotion.features.profile.domain.model.UserProfile
import kotlinx.coroutines.tasks.await

class FirestoreTopicServiceImpl (
    private val firestore: FirebaseFirestore
) : FirestoreTopicService {

    private val topicsCollection = firestore.collection("topics")

    override suspend fun getAllTopics(): Either<NetworkError, List<Topic>> = either {
        return Either.catch {
            val snapshot = firestore.collection("topics")
                .get()
                .await()

            snapshot.documents.mapNotNull { doc ->
                doc.toObject(TopicDTO::class.java)?.toDomain()
            }
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getTopicByName(topicName: String): Either<NetworkError, Topic> {
        return Either.catch {
            val snapshot = firestore.collection("topics")
                .whereEqualTo("name", topicName)
                .limit(1)
                .get()
                .await()

            val document = snapshot.documents.firstOrNull()
                ?: throw NoSuchElementException("Topic with name '$topicName' not found")

            val dto = document.toObject(TopicDTO::class.java)
                ?: throw IllegalStateException("Invalid topic document")

            dto.toDomain()
        }.mapLeft { it.toNetworkError() }
    }


}
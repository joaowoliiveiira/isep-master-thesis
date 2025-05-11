package com.student.mentalpotion.features.activities.data.service

import arrow.core.Either
import com.student.mentalpotion.features.activities.domain.model.Topic
import com.student.mentalpotion.core.util.NetworkError

interface FirestoreTopicService {
    suspend fun getAllTopics(): Either<NetworkError, List<Topic>>
    suspend fun getTopicByName(topicName: String): Either<NetworkError, Topic>
}
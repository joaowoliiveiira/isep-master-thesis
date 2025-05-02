package com.student.mentalpotion.features.activities.data.service

import arrow.core.Either
import com.student.mentalpotion.features.activities.domain.model.Topic
import com.student.mentalpotion.features.authentication.domain.model.NetworkError
import com.student.mentalpotion.features.profile.domain.model.UserProfile

interface FirestoreTopicService {
    suspend fun getAllTopics(): Either<NetworkError, List<Topic>>
    suspend fun getTopicByName(topicName: String): Either<NetworkError, Topic>
}
package com.student.mentalpotion.features.activities.domain.repository

import arrow.core.Either
import com.student.mentalpotion.features.activities.domain.model.Topic
import com.student.mentalpotion.core.util.NetworkError

interface TopicRepository {
    suspend fun getAllTopics(): Either<NetworkError, List<Topic>>
    // Name should be unique for Topics
    suspend fun getTopicByName(topicName: String): Either<NetworkError, Topic>
}
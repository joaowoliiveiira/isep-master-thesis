package com.student.mentalpotion.features.activities.domain.repository

import arrow.core.Either
import com.student.mentalpotion.features.activities.domain.model.Activity
import com.student.mentalpotion.features.activities.domain.model.Topic
import com.student.mentalpotion.features.authentication.domain.model.NetworkError
import com.student.mentalpotion.features.profile.domain.model.UserProfile

interface TopicRepository {
    suspend fun getAllTopics(): Either<NetworkError, List<Topic>>
    // Name should be unique for Topics
    suspend fun getTopicByName(topicName: String): Either<NetworkError, Topic>
}
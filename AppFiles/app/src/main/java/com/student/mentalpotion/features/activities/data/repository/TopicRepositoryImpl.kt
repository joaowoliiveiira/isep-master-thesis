package com.student.mentalpotion.features.activities.data.repository

import arrow.core.Either
import com.student.mentalpotion.features.activities.data.service.FirestoreTopicService
import com.student.mentalpotion.features.activities.domain.model.Activity
import com.student.mentalpotion.features.activities.domain.model.Topic
import com.student.mentalpotion.features.activities.domain.repository.TopicRepository
import com.student.mentalpotion.features.authentication.domain.model.NetworkError
import com.student.mentalpotion.features.profile.domain.model.UserProfile

class TopicRepositoryImpl(
    private val service: FirestoreTopicService
) : TopicRepository {

    override suspend fun getAllTopics(): Either<NetworkError, List<Topic>> {
        return service.getAllTopics()
    }

    override suspend fun getTopicByName(topicName: String): Either<NetworkError, Topic> {
        return service.getTopicByName(topicName)
    }
}
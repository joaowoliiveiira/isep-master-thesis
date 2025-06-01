package com.student.mpbackoffice.features.data_manager.domain.repository

import com.student.mpbackoffice.features.data_manager.domain.model.Topic

interface TopicRepository {
    suspend fun getAllTopics(): Result<List<Topic>>
    suspend fun addTopic(topic: Topic): Result<Unit>
    suspend fun updateTopic(topic: Topic): Result<Unit>
    suspend fun deleteTopic(id: String): Result<Unit>
}
package com.student.mpbackoffice.features.data_manager.data.repository

import com.student.mpbackoffice.features.data_manager.data.remote.dto.TopicDto
import com.student.mpbackoffice.features.data_manager.domain.model.Topic
import com.student.mpbackoffice.features.data_manager.domain.repository.TopicRepository
import dev

class TopicRepositoryImpl(
    private val firestore: FirebaseFirestore = FirebaseFirestore()
) : TopicRepository {

    private val topicCollection = firestore.collection("topics")

    override suspend fun getAllTopics(): Result<List<Topic>> = runCatchingResult {
        val snapshots = topicCollection.get().documents
        snapshots.mapNotNull { it.data()?.let { dto -> dto.toObject<TopicDto>().toDomain() } }
    }

    override suspend fun addTopic(topic: Topic): Result<Unit> = runCatchingResult {
        topicCollection.document(topic.id).set(TopicDto.fromDomain(topic))
    }

    override suspend fun updateTopic(topic: Topic): Result<Unit> = runCatchingResult {
        topicCollection.document(topic.id).set(TopicDto.fromDomain(topic))
    }

    override suspend fun deleteTopic(id: String): Result<Unit> = runCatchingResult {
        topicCollection.document(id).delete()
    }
}
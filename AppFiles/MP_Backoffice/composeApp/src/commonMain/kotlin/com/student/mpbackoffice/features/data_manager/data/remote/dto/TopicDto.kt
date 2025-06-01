package com.student.mpbackoffice.features.data_manager.data.remote.dto

import com.student.mpbackoffice.features.data_manager.domain.model.Topic

@kotlinx.serialization.Serializable
data class TopicDto(
    val id: String,
    val title: String,
    val description: String
) {
    fun toDomain(): Topic = Topic(id, title, description)

    companion object {
        fun fromDomain(topic: Topic): TopicDto =
            TopicDto(topic.id, topic.title, topic.description)
    }
}
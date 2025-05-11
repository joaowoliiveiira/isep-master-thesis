package com.student.mentalpotion.features.activities.data.mapper

import com.student.mentalpotion.features.activities.domain.model.Topic

data class TopicDTO(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val imageUrl: String = ""
) {
    fun toDomain() = Topic(id, name, description, imageUrl)

    fun toDto() = TopicDTO(id, name, description, imageUrl)
}
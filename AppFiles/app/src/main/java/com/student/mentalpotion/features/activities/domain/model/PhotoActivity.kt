package com.student.mentalpotion.features.activities.domain.model

data class PhotoActivity(
    override val id: String,
    override val topicId: String,
    override val title: String,
    override val description: String,
    override val difficulty: DifficultyLevel,
    val prompt: String
) : Activity()
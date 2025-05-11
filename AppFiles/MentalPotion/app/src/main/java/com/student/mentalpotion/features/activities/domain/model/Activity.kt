package com.student.mentalpotion.features.activities.domain.model

sealed class Activity {
    abstract val id: String
    abstract val topicId: String
    abstract val title: String
    abstract val description: String
    abstract val difficulty: DifficultyLevel
}
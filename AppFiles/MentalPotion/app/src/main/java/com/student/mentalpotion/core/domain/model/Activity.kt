package com.student.mentalpotion.core.domain.model

enum class ActivityType { TIME_BASED, TEXT, RATING, PHOTO }

data class Activity(
    val id: String,
    val title: String,
    val description: String,
    val type: ActivityType,
    val difficulty: Int
)
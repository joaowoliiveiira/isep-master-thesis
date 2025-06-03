package com.student.mentalpotion.core.domain.model

data class ActivityInstance(
    val id: String,
    val userRoutineId: String,
    val activityId: String,
    val scheduledTime: Long,
    val completed: Boolean,
    val sourceRoutineId: String
)
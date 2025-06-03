package com.student.mentalpotion.core.domain.model

data class ActivityResponse(
    val id: String,
    val activityInstanceId: String,
    val responseText: String?,
    val rating: Int?,
    val photoUrl: String?,
    val timeSpentMinutes: Int
)
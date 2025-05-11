package com.student.mentalpotion.features.activities.domain.model

data class Topic(
    val id: String,
    val name: String,
    val description: String,
    val iconUrl: String? = null
)
package com.student.mentalpotion.features.profile.domain.model

data class UserProfile(
    val uid: String,
    val email: String = "",
    val username: String = "",
    val avatarUrl: String = "",
    val bio: String = "",
    val goals: List<String> = emptyList()
)
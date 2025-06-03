package com.student.mentalpotion.core.domain.model

data class UserAvatar(
    val userId: String,
    val avatarId: String,
    val unlockedAt: Long
)

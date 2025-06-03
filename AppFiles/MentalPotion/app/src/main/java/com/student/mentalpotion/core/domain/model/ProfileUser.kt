package com.student.mentalpotion.core.domain.model

data class ProfileUser(
    val uid: String,
    val name: String,
    val email: String,
    val avatarId: String?,
    val createdAt: Long
)
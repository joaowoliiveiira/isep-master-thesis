package com.student.mentalpotion.core.domain.model

data class UserSkill(
    val userId: String,
    val skillId: String,
    val level: Int,
    val xp: Int
)
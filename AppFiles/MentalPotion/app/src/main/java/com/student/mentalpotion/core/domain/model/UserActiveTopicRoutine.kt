package com.student.mentalpotion.core.domain.model

data class UserActiveTopicRoutine(
    val userRoutineId: String,
    val routineTemplateId: String,
    val activatedAt: Long
)
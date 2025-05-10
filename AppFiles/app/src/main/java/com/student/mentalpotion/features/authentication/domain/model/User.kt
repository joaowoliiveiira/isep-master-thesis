package com.student.mentalpotion.features.authentication.domain.model

data class User(
    val uid: String,
    val email: String,
    val name: String? = null
)
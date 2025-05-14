package com.student.mpbackoffice.features.authentication.domain.model

data class Admin(
    val uid: String,
    val email: String,
    val token: String // Auth token or session ID returned by server
)
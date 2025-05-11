package com.student.mpbackoffice.features.authentication.domain.model

data class Admin(
    val id: String,
    val email: String,
    val displayName: String? = null,
    val token: String // Auth token or session ID returned by server
)
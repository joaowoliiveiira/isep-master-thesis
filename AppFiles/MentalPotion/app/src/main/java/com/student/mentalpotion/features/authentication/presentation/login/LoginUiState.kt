package com.student.mentalpotion.features.authentication.presentation.login

import com.student.mentalpotion.features.authentication.domain.model.AuthUser

data class LoginUiState(
    val isLoading: Boolean = false,
    val user: AuthUser? = null,
    val error: String? = null
)

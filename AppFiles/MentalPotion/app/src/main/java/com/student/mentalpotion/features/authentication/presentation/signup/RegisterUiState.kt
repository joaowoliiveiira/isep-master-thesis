package com.student.mentalpotion.features.authentication.presentation.signup

import com.student.mentalpotion.core.domain.model.RegisteredUser

data class RegisterUiState(
    val isLoading: Boolean = false,
    val user: RegisteredUser? = null,
    val error: String? = null
)

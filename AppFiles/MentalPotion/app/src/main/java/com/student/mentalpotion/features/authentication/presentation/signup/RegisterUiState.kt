package com.student.mentalpotion.features.authentication.presentation.signup

import com.student.mentalpotion.core.domain.model.RegisteredUser
import com.student.mentalpotion.core.util.NetworkError

data class RegisterUiState(
    val isLoading: Boolean = false,
    val user: RegisteredUser? = null,
    val error: NetworkError? = null
)

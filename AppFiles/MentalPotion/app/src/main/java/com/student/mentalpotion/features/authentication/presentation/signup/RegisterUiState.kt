package com.student.mentalpotion.features.authentication.presentation.signup

import com.student.mentalpotion.core.domain.model.RegisteredUser

sealed class RegisterUiState {
    data object Idle : RegisterUiState()
    data object Loading : RegisterUiState()
    data class Success(val user: RegisteredUser) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}

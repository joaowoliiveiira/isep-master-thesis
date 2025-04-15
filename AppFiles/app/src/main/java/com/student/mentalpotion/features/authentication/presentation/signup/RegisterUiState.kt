package com.student.mentalpotion.features.authentication.presentation.signup

import com.student.mentalpotion.features.authentication.domain.model.User

sealed class RegisterUiState {
    object Idle : RegisterUiState()
    object Loading : RegisterUiState()
    data class Success(val user: User) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}
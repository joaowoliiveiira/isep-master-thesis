package com.student.mentalpotion.features.authentication.presentation.login

import com.student.mentalpotion.features.authentication.domain.model.User

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val user: User) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}
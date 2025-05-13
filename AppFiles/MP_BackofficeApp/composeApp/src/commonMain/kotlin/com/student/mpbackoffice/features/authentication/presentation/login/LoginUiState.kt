package com.student.mpbackoffice.features.authentication.presentation.login

import com.student.mpbackoffice.features.authentication.domain.model.Admin

sealed interface LoginUiState {
    object Idle : LoginUiState
    object Loading : LoginUiState
    object Success : LoginUiState
    data class Error(val message: String) : LoginUiState
}
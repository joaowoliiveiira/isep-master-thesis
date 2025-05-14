package com.student.mpbackoffice.features.authentication.presentation.login

import com.student.mpbackoffice.features.authentication.domain.usecase.LoginUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Expect declaration for MP (shared module)
expect open class MPViewModel() {
    val coroutineScope: CoroutineScope
}

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : MPViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onLoginClick() {
        val currentEmail = email.value
        val currentPassword = password.value

        if (currentEmail.isBlank() || currentPassword.isBlank()) {
            _uiState.value = LoginUiState.Error("Email and password must not be empty")
            return
        }

        coroutineScope.launch {
            _uiState.value = LoginUiState.Loading

            val result = loginUseCase(currentEmail, currentPassword)
            _uiState.value = result.fold(
                onSuccess = { LoginUiState.Success },
                onFailure = { LoginUiState.Error(it.message ?: "Unknown error") }
            )
        }
    }

    fun resetUiState() {
        _uiState.value = LoginUiState.Idle
    }
}
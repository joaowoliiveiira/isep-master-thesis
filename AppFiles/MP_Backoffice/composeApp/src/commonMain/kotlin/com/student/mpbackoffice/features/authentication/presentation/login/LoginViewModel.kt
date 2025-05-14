package com.student.mpbackoffice.features.authentication.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnLoginClick -> TODO()
            is LoginAction.OnPasswordChanged -> TODO()
            is LoginAction.OnRegisterClick -> TODO()
            is LoginAction.OnUsernameChanged -> TODO()
        }
    }
}
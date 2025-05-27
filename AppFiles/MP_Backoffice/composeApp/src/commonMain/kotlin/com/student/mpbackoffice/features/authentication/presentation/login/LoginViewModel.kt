package com.student.mpbackoffice.features.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.mpbackoffice.features.authentication.domain.repository.AuthRepository
import com.student.mpbackoffice.core.domain.onError
import com.student.mpbackoffice.core.domain.onSuccess
import com.student.mpbackoffice.core.presentation.toUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnEmailChanged -> _state.update { it.copy(email = action.email) }
            is LoginAction.OnPasswordChanged -> _state.update { it.copy(password = action.password) }
            is LoginAction.OnLoginClick -> login()
            is LoginAction.OnRegisterClick -> { /* Handle navigation to register screen */ }
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true, errorMessage = null
                )
            }

            authRepository
                .login(_state.value.email, _state.value.password)
                .onSuccess {
                    _state.update {
                        it.copy(
                            isLoading = true,
                            errorMessage = null,
                            loginSuccessful = true
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.toUiText(),
                            loginSuccessful = false
                        )
                    }
                }
        }
    }
}

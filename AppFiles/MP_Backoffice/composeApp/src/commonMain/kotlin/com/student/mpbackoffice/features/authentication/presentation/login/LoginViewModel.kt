package com.student.mpbackoffice.features.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.mpbackoffice.core.domain.Error
import com.student.mpbackoffice.core.domain.Result
import com.student.mpbackoffice.core.presentation.UiText
import com.student.mpbackoffice.features.authentication.domain.repository.AuthRepository
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
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            val result = authRepository.login(_state.value.email, _state.value.password)

            _state.update {
                when (result) {
                    is Result.Success -> it.copy(
                        isLoading = false,
                        loginSuccessful = true
                    )
                    is Result.Failure -> it.copy(
                        isLoading = false,
                        errorMessage = result.toUiText()
                    )
                }
            }

        }
    }
}

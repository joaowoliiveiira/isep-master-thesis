package com.student.mpbackoffice.features.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.mpbackoffice.features.authentication.data.SupabaseAuthRepository
import com.student.mpbackoffice.core.presentation.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: SupabaseAuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnUsernameChanged -> {
                _state.value = _state.value.copy(username = action.username)
            }
            is LoginAction.OnPasswordChanged -> {
                _state.value = _state.value.copy(password = action.password)
            }
            is LoginAction.OnLoginClick -> {
                login(action.email, action.password)
            }
            is LoginAction.OnRegisterClick -> {
                //handle registration
            }
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, errorMeesage = null)

            val result = authRepository.login(email, password)

            _state.value = if (result.isSuccess) {
                // Success — update state, navigate, etc.
                _state.value.copy(isLoading = false)
            } else {
                // Failure — show error message
                _state.value.copy(
                    isLoading = false,
                    errorMeesage = UiText.DynamicString(
                        result.exceptionOrNull()?.message ?: "Unknown error"
                    )
                )
            }
        }
    }

}

package com.student.mpbackoffice.features.authentication.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.mpbackoffice.core.presentation.UiText
import com.student.mpbackoffice.features.authentication.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignupViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    fun onAction(action: SignupAction) {
        when (action) {
            is SignupAction.OnEmailChanged -> {
                _state.value = _state.value.copy(email = action.email)
            }
            is SignupAction.OnPasswordChanged -> {
                _state.value = _state.value.copy(password = action.password)
            }
            is SignupAction.OnSignupClick -> {
                signup(action.email, action.password)
            }
        }
    }

    private fun signup(email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, errorMessage = null)

            val result = authRepository.signup(email, password)

            _state.value = if (result.isSuccess) {
                _state.value.copy(isLoading = false)
            } else {
                _state.value.copy(
                    isLoading = false,
                    errorMessage = UiText.DynamicString(
                        result.exceptionOrNull()?.message ?: "Unknown error"
                    )
                )
            }
        }
    }
}


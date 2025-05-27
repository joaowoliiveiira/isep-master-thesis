package com.student.mpbackoffice.features.authentication.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.mpbackoffice.core.domain.onError
import com.student.mpbackoffice.core.domain.onSuccess
import com.student.mpbackoffice.core.presentation.UiText
import com.student.mpbackoffice.core.presentation.toUiText
import com.student.mpbackoffice.features.authentication.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignupViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    private val _effect = MutableStateFlow<SignupEffect?>(null)
    val effect = _effect.asStateFlow()

    fun onAction(action: SignupAction) {
        when (action) {
            is SignupAction.OnEmailChanged -> {
                _state.update { it.copy(email = action.email) }
            }
            is SignupAction.OnPasswordChanged -> {
                _state.update { it.copy(password = action.password) }
            }
            is SignupAction.OnSignupClick -> {
                validateAndSignup(action.email, action.password)
            }
            is SignupAction.OnBackToLoginClick -> {
                _effect.value = SignupEffect.NavigateBackToLogin
            }
        }
    }

    private fun validateAndSignup(email: String, password: String) {
        val isEmailValid = email.isNotBlank() && email.contains("@")
        val isPasswordValid = password.isNotBlank()

        if (!isEmailValid || !isPasswordValid) {
            _state.update {
                it.copy(
                    errorMessage = UiText.DynamicString("Invalid email or password")
                )
            }
            return
        }

        signup(email, password)
    }

    private fun signup(email: String, password: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            authRepository.signup(email, password)
                .onSuccess {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            loginSuccessful = true
                        )
                    }
                    _effect.value = SignupEffect.SignupSuccess
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

    fun clearEffect() {
        _effect.value = null
    }
}

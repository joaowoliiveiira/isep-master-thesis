package com.student.mpbackoffice.features.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.mpbackoffice.core.domain.onError
import com.student.mpbackoffice.core.domain.onSuccess
import com.student.mpbackoffice.core.presentation.UiText
import com.student.mpbackoffice.core.presentation.toUiText
import com.student.mpbackoffice.features.authentication.domain.repository.AuthRepository
import io.github.jan.supabase.auth.exception.AuthRestException
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
            is LoginAction.OnRegisterClick -> _state.update { it.copy(navigateToSignup = true) }
        }
    }

    private fun login() {
        val email = _state.value.email.trim()
        val password = _state.value.password

        // Input validation
        if (email.isBlank() || password.isBlank()) {
            _state.update {
                it.copy(
                    errorMessage = UiText.DynamicString("Email and password must not be blank")
                )
            }
            return
        }

        if (!email.contains("@")) {
            _state.update {
                it.copy(
                    errorMessage = UiText.DynamicString("Please enter a valid email address")
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                authRepository.login(email, password)
                    .onSuccess {
                        _state.update {
                            it.copy(
                                isLoading = false,
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
            } catch (e: AuthRestException) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = UiText.DynamicString(e.message.toString()),
                        loginSuccessful = false
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = UiText.DynamicString(e.message.toString()),
                        loginSuccessful = false
                    )
                }
            }
        }
    }

    // Reset one-time flags after being handled in UI
    fun onNavigatedToRegister() {
        _state.update { it.copy(navigateToSignup = false) }
    }

    fun onLoginHandled() {
        _state.update { it.copy(loginSuccessful = false) }
    }
}
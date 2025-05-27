package com.student.mpbackoffice.features.authentication.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.mpbackoffice.core.presentation.UiText
import com.student.mpbackoffice.features.authentication.domain.repository.AuthRepository
import com.student.mpbackoffice.core.domain.onError
import com.student.mpbackoffice.core.domain.onSuccess
import com.student.mpbackoffice.core.presentation.toUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
            is SignupAction.OnBackToLoginClick -> TODO()
        }
    }

    private fun signup(email: String, password: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true, errorMessage = null
                )
            }

            authRepository
                .signup(_state.value.email, _state.value.password)
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


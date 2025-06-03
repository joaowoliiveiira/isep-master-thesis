package com.student.mentalpotion.features.authentication.presentation.login

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.mentalpotion.core.util.Result
import com.student.mentalpotion.features.authentication.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")

    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginState: StateFlow<LoginUiState> = _loginState

    fun onLoginClick() {
        if (email.isBlank() || password.isBlank()) {
            _loginState.value = LoginUiState.Error("Email and password must not be empty")
            return
        }

        viewModelScope.launch {
            _loginState.value = LoginUiState.Loading

            when (val result = loginUseCase(email, password)) {
                is Result.Success -> _loginState.value = LoginUiState.Success(result.data)
                is Result.Error -> _loginState.value = LoginUiState.Error(result.error.message)
            }
        }
    }

    fun resetUiState() {
        _loginState.value = LoginUiState.Idle
    }
}

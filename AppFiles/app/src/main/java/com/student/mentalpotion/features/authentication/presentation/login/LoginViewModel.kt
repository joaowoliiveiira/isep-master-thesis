package com.student.mentalpotion.features.authentication.presentation.login

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.student.mentalpotion.features.authentication.domain.repository.AuthenticationRepository
import com.student.mentalpotion.features.authentication.domain.usecase.LoginUseCase
import com.student.mentalpotion.features.authentication.presentation.login.LoginUiState
import com.student.mentalpotion.features.authentication.presentation.login.LoginUiState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var loginState by mutableStateOf<LoginUiState>(LoginUiState.Idle)
        private set

    fun onLoginClick() {
        if (email.isBlank() || password.isBlank()) {
            loginState = LoginUiState.Error("Email and password must not be empty")
            return
        }

        viewModelScope.launch {
            loginState = LoginUiState.Loading
            when (val result = loginUseCase(email, password)) {
                is Either.Right -> {
                    loginState = Success(result.value)
                }
                is Either.Left -> {
                    loginState = Error(result.value.error.message)
                }

                is Either.Left<*> -> TODO()
                is Either.Right<*> -> TODO()
            }
        }
    }

    fun resetUiState() {
        loginState = LoginUiState.Idle
    }
}

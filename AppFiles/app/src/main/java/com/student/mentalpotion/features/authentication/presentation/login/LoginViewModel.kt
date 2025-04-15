package com.student.mentalpotion.features.authentication.presentation.login

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.mentalpotion.features.authentication.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var uiState by mutableStateOf<LoginUiState>(LoginUiState.Idle)
        private set

    fun onLoginClick() {
        viewModelScope.launch {
            uiState = LoginUiState.Loading

            val result = loginUseCase(email, password)

            uiState = result.fold(
                ifLeft = { LoginUiState.Error(it.error.message) },
                ifRight = { LoginUiState.Success(it) }
            )
        }
    }

    fun resetUiState() {
        uiState = LoginUiState.Idle
    }
}
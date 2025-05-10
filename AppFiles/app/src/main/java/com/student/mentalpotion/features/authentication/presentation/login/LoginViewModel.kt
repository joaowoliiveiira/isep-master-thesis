package com.student.mentalpotion.features.authentication.presentation.login

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.student.mentalpotion.features.authentication.domain.model.User
import com.student.mentalpotion.features.authentication.domain.repository.AuthenticationRepository
import com.student.mentalpotion.features.authentication.domain.usecase.LoginUseCase
import com.student.mentalpotion.features.authentication.presentation.login.LoginUiState
import com.student.mentalpotion.features.authentication.presentation.login.LoginUiState.*
import com.student.mentalpotion.features.profile.domain.usecase.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase
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

            // When we acquire a result for the User
            when (val result = loginUseCase(email, password)) {
                is Either.Right -> {
                    val user = result.value

                    // When we acquire a result for the UserProfile, might use it later :D
                    when (val profileResult = getUserProfileUseCase(user.id)) {
                        is Either.Right -> {
                            loginState = LoginUiState.Success(user)
                        }
                        is Either.Left -> {
                            loginState = LoginUiState.Error("User profile not found.")
                            // PErhaps navigate to a profile setup screen here?
                        }
                    }
                }

                is Either.Left -> {
                    loginState = LoginUiState.Error(result.value.error.message ?: "Unknown error")
                }
            }
        }
    }

    fun resetUiState() {
        loginState = LoginUiState.Idle
    }
}

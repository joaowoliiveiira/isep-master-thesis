package com.student.mentalpotion.features.authentication.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.student.mentalpotion.features.authentication.domain.usecase.RegisterUseCase
import com.student.mentalpotion.features.authentication.presentation.login.LoginUiState
import com.student.mentalpotion.features.profile.domain.model.UserProfile
import com.student.mentalpotion.features.profile.domain.usecase.CreateUserProfileUseCase
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val createUserProfileUseCase: CreateUserProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun register(email: String, password: String) {

        // Check if the fields are not blank
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = RegisterUiState.Error("Email and password cannot be empty")
            return
        }

        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading

            // Create the user and a userProfile
            when (val result = registerUseCase(email, password)) {
                is Either.Right -> {
                    val user = result.value

                    // Create user profile with default avatar
                    val profile = UserProfile(
                        uid = user.uid,
                        email = user.email,
                        username = extractUsernameFromEmail(user.email),
                        //avatarUrl = "avatar_1"
                    )

                    when (val profileResult = createUserProfileUseCase(profile)) {
                        is Either.Right -> {
                            _uiState.value = RegisterUiState.Success(user)
                        }

                        is Either.Left -> {
                            _uiState.value =
                                RegisterUiState.Error("Failed to create profile: ${profileResult.value.error.message}")
                        }
                    }
                }

                is Either.Left -> {
                    _uiState.value = RegisterUiState.Error(result.value.error.message)
                }
            }
        }
    }

    fun resetState() {
        _uiState.value = RegisterUiState.Idle
    }

    private fun extractUsernameFromEmail(email: String): String {
        return email.substringBefore("@").replace(".", "_")
    }
}
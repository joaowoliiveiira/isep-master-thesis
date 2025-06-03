package com.student.mentalpotion.features.authentication.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.mentalpotion.core.util.Result
import com.student.mentalpotion.features.authentication.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _registerState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val registerState: StateFlow<RegisterUiState> = _registerState

    fun register(email: String, password: String, username: String, avatarId: String? = null) {
        if (email.isBlank() || password.isBlank() || username.isBlank()) {
            _registerState.value = RegisterUiState.Error("Email, username, and password cannot be empty")
            return
        }

        viewModelScope.launch {
            _registerState.value = RegisterUiState.Loading

            when (val result = registerUseCase(email, password, username, avatarId)) {
                is Result.Success -> _registerState.value = RegisterUiState.Success(result.data)
                is Result.Error -> _registerState.value = RegisterUiState.Error(result.error.message)
            }
        }
    }

    fun resetState() {
        _registerState.value = RegisterUiState.Idle
    }
}

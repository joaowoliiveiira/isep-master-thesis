package com.student.mentalpotion.features.authentication.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.student.mentalpotion.features.authentication.domain.usecase.RegisterUseCase

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading

            registerUseCase(email, password).fold(
                ifLeft = { error ->
                    _uiState.value = RegisterUiState.Error(error.error.message)
                },
                ifRight = { user ->
                    _uiState.value = RegisterUiState.Success(user)
                }
            )
        }
    }

    fun resetState() {
        _uiState.value = RegisterUiState.Idle
    }
}
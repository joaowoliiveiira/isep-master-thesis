package com.student.mentalpotion.features.authentication.presentation.splash

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.mentalpotion.core.util.Result
import com.student.mentalpotion.features.authentication.domain.usecase.CheckSessionUseCase
import com.student.mentalpotion.features.authentication.domain.usecase.LoginUseCase
import com.student.mentalpotion.features.authentication.presentation.signup.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkSessionUseCase: CheckSessionUseCase
) : ViewModel() {

    private val _splashState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val splashState: StateFlow<SplashUiState> = _splashState

    init {
        viewModelScope.launch {
            checkSession()
        }
    }

    private suspend fun checkSession() {
        val result = checkSessionUseCase()
        _splashState.value = if (result is Result.Success) {
            SplashUiState.NavigateToMain
        } else {
            SplashUiState.NavigateToAuth
        }
    }
}
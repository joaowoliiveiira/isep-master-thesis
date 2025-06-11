package com.student.mentalpotion.features.authentication.presentation.splash

sealed class SplashUiState {
    object Loading : SplashUiState()
    object NavigateToAuth : SplashUiState()
    object NavigateToMain : SplashUiState()
}
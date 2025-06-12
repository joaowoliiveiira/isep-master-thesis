package com.student.mentalpotion.features.authentication.presentation.splash

data class SplashUiState(
    val isLoading: Boolean = true,
    val navigateToMain: Boolean = false,
    val navigateToAuth: Boolean = false
)
package com.student.mpbackoffice.features.authentication.presentation.login

import com.student.mpbackoffice.core.presentation.UiText

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
    val loginSuccessful: Boolean = false,
    val navigateToSignup: Boolean = false
)
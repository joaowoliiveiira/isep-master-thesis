package com.student.mpbackoffice.features.authentication.presentation.signup

import com.student.mpbackoffice.core.presentation.UiText

data class SignupState(
    val email: String = "Email",
    val password: String = "",
    val isLoading: Boolean = false,
    val loginSuccessful: Boolean = false,
    val errorMessage: UiText? = null
)
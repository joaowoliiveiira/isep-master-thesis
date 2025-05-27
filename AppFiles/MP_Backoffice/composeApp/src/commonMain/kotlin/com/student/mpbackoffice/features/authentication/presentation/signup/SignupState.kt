package com.student.mpbackoffice.features.authentication.presentation.signup

import com.student.mpbackoffice.core.presentation.UiText

data class SignupState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
    val loginSuccessful: Boolean = false
)
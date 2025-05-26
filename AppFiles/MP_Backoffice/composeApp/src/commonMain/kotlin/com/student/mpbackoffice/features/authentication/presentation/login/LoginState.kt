package com.student.mpbackoffice.features.authentication.presentation.login

import com.student.mpbackoffice.core.presentation.UiText

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val loginSuccessful: Boolean = false,
    val errorMessage: UiText? = null
)
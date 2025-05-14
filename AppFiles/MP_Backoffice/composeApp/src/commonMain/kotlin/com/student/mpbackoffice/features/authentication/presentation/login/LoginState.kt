package com.student.mpbackoffice.features.authentication.presentation.login

import com.student.mpbackoffice.core.presentation.UiText

data class LoginState(
    val username: String = "Username",
    val password: String = "Password",
    val isLoading: Boolean = false,
    val errorMeesage: UiText? = null
)
package com.student.mpbackoffice.features.authentication.presentation.login

sealed interface LoginAction {
    data class OnUsernameChanged(val username: String) : LoginAction
    data class OnPasswordChanged(val password: String) : LoginAction
    data class OnLoginClick(val email: String, val password: String) : LoginAction
    data class OnRegisterClick(val email: String) : LoginAction
}
package com.student.mpbackoffice.features.authentication.presentation.signup

sealed interface SignupAction {
    data class OnEmailChanged(val email: String) : SignupAction
    data class OnPasswordChanged(val password: String) : SignupAction
    data class OnSignupClick(val email: String, val password: String) : SignupAction
}
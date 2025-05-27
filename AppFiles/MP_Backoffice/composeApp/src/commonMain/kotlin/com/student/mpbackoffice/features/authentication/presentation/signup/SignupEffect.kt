package com.student.mpbackoffice.features.authentication.presentation.signup

sealed interface SignupEffect {
    object SignupSuccess : SignupEffect
    object NavigateBackToLogin : SignupEffect
}
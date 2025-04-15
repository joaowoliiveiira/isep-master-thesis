package com.student.mentalpotion.features.authentication.presentation.navigation

sealed class AuthenticationDestinations(val route: String) {
    object Login : AuthenticationDestinations("login")
    object Register : AuthenticationDestinations("register")
    object Home : AuthenticationDestinations("home")
}
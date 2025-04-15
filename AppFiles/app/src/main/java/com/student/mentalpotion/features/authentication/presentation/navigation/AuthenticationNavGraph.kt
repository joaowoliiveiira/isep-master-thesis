package com.student.mentalpotion.features.authentication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.student.mentalpotion.features.authentication.domain.model.User
import com.student.mentalpotion.features.authentication.presentation.login.LoginScreen

@Composable
fun AuthenticationNavGraph (
    navController: NavHostController,
    startDestination: String = AuthenticationDestinations.Login.route,
    onLoginSuccess: (User) -> Unit
) {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(AuthenticationDestinations.Login.route) {
            LoginScreen(
                onLoginSuccess = { user ->
                    onLoginSuccess(user)
                }
            )
        }

        // Optional future screens
        // composable(AuthDestinations.Register.route) {
        //     RegisterScreen(...)
        // }
    }
}
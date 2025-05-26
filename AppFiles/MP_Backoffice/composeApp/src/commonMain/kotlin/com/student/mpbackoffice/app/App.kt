package com.student.mpbackoffice.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.student.mpbackoffice.features.authentication.presentation.login.LoginScreenRoot
import com.student.mpbackoffice.features.authentication.presentation.login.LoginViewModel
import io.ktor.server.routing.route
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.LoginPage
        ) {
            navigation<Route.LoginGraph>(
                startDestination = Route.LoginPage
            ) {
                composable<Route.LoginPage> {
                    LoginScreenRoot(
                        onLoginClick = { email, password ->
                            // navigate to next page or show toast
                        },
                        onRegisterClick = { email ->
                            // navigate to register page
                        }
                    )
                }
            }
        }
    }
}
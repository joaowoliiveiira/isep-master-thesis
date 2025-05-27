package com.student.mpbackoffice.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.student.mpbackoffice.features.authentication.presentation.login.LoginScreenRoot
import com.student.mpbackoffice.features.authentication.presentation.signup.SignupScreenRoot
import com.student.mpbackoffice.features.home.presentation.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.LoginGraph
        ) {
            // Auth Graph
            navigation<Route.LoginGraph>(
                startDestination = Route.LoginPage
            ) {
                composable<Route.LoginPage> {
                    LoginScreenRoot(
                        onLoginSuccess = {
                            navController.navigate(Route.HomePage) {
                                popUpTo(Route.LoginPage) { inclusive = true }
                            }
                        },
                        onNavigateToSignup = {
                            navController.navigate(Route.SignupPage)
                        }
                    )
                }

                composable<Route.SignupPage> {
                    SignupScreenRoot(
                        onSignupSuccess = {
                            navController.navigate(Route.HomePage) {
                                popUpTo(Route.LoginPage) { inclusive = true }
                            }
                        },
                        onBackToLogin = {
                            navController.popBackStack()
                        }
                    )
                }
            }

            // Home Page
            composable<Route.HomePage> {
                HomeScreen()
            }
        }
    }
}
package com.student.mpbackoffice.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.student.mpbackoffice.features.authentication.presentation.login.LoginScreenRoot
import com.student.mpbackoffice.features.authentication.presentation.login.LoginViewModel
import com.student.mpbackoffice.features.authentication.presentation.signup.SignupScreenRoot
import com.student.mpbackoffice.features.authentication.presentation.signup.SignupViewModel
import com.student.mpbackoffice.features.home.presentation.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

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
                    val viewModel = koinViewModel<LoginViewModel>()

                    LoginScreenRoot(
                        viewModel = viewModel,
                        onLoginClick = { _, _ ->
                            // TODO: validate credentials before navigation if needed
                            navController.navigate(Route.HomePage)
                        },
                        onRegisterClick = {
                            navController.navigate(Route.SignupPage)
                        }
                    )
                }

                composable<Route.SignupPage> {
                    val viewModel = koinViewModel<SignupViewModel>()

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

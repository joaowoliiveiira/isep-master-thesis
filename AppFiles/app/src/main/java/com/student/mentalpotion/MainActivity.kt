package com.student.mentalpotion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.student.mentalpotion.features.authentication.presentation.login.LoginScreen
import com.student.mentalpotion.features.authentication.presentation.navigation.AuthenticationDestinations
import com.student.mentalpotion.features.authentication.presentation.signup.RegisterScreen
import com.student.mentalpotion.ui.HomeScreen
import com.student.mentalpotion.ui.theme.MentalPotionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MentalPotionTheme {
                val navController = rememberNavController()

                Surface(modifier = Modifier) {
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AuthenticationDestinations.Login.route
    ) {
        // Directly call composables here - no nested NavHost for now
        composable(AuthenticationDestinations.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AuthenticationDestinations.Home.route) {
                        popUpTo(AuthenticationDestinations.Login.route) { inclusive = true }
                    }
                },
                navController = navController
            )
        }

        composable(AuthenticationDestinations.Register.route) {
            RegisterScreen(
                onRegisterSuccess = { user ->
                    navController.navigate(AuthenticationDestinations.Home.route) {
                        popUpTo(AuthenticationDestinations.Register.route) { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.navigate(AuthenticationDestinations.Login.route) {
                        popUpTo(AuthenticationDestinations.Register.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AuthenticationDestinations.Home.route) {
            HomeScreen()
        }
    }
}

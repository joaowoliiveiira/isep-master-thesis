package com.student.mentalpotion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.student.mentalpotion.features.authentication.presentation.login.LoginScreen
import com.student.mentalpotion.core.navigation.AppDestinations
import com.student.mentalpotion.features.authentication.presentation.login.LandingScreen
import com.student.mentalpotion.features.authentication.presentation.login.LoginViewModel
import com.student.mentalpotion.features.authentication.presentation.signup.RegisterScreen
import com.student.mentalpotion.ui.HomeScreen
import com.student.mentalpotion.ui.components.BottomNavBar
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

/**
 * AppNavHost manages both authentication flow and main app flow.
 */
@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        // First screen to appear when opening
        startDestination = AppDestinations.Landing.route
    ) {
        composable(AppDestinations.Landing.route) {
            LandingScreen(
                navController = navController
            )
        }

        composable(AppDestinations.Login.route) {
            val viewModel: LoginViewModel = hiltViewModel()

            LoginScreen(
                onLoginSuccess = { user ->
                    navController.navigate(AppDestinations.Home.route) {
                        popUpTo(AppDestinations.Login.route) { inclusive = true }
                    }
                },
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(AppDestinations.Register.route) {
            RegisterScreen(
                onRegisterSuccess = { user ->
                    navController.navigate(AppDestinations.Home.route) {
                        popUpTo(AppDestinations.Register.route) { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.navigate(AppDestinations.Login.route) {
                        popUpTo(AppDestinations.Register.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppDestinations.Home.route) {
            MainScaffold(navController = navController)
        }
    }
}

/**
 * MainScaffold manages the bottom bar and main in-app screens.
 */
@Composable
fun MainScaffold(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navController,
                onItemClick = { destination ->
                    navController.navigate(destination.route) {
                        // Avoid duplicating same destination in backstack
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestinations.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppDestinations.Home.route) {
                HomeScreen()
            }

            // Uncomment when ready
            /*
            composable(AppDestinations.Activities.route) {
                ActivitiesScreen()
            }

            composable(AppDestinations.Profile.route) {
                ProfileScreen()
            }
            */
        }
    }
}
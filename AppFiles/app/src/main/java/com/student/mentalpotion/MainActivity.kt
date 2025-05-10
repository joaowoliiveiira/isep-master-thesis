package com.student.mentalpotion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
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
import com.student.mentalpotion.features.activities.presentation.topics.TopicListScreen
import com.student.mentalpotion.features.authentication.presentation.login.LandingScreen
import com.student.mentalpotion.features.authentication.presentation.login.LoginViewModel
import com.student.mentalpotion.features.authentication.presentation.signup.RegisterScreen
import com.student.mentalpotion.features.profile.presentation.home.HomeScreen
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
                val rootNavController = rememberNavController()

                Surface(modifier = Modifier.fillMaxSize()) {
                    RootNavHost(navController = rootNavController)
                }
            }
        }
    }
}

@Composable
fun RootNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        // Navigation for "outside" of the app content
        navigation(startDestination = AppDestinations.Landing.route, route = "auth") {
            composable(AppDestinations.Landing.route) {
                LandingScreen(navController)
            }

            composable(AppDestinations.Login.route) {
                val viewModel: LoginViewModel = hiltViewModel()
                LoginScreen(
                    navController = navController,
                    viewModel = viewModel,
                    onLoginSuccess = {
                        navController.navigate("main") {
                            popUpTo("auth") { inclusive = true }
                        }
                    }
                )
            }

            composable(AppDestinations.Register.route) {
                RegisterScreen(
                    onRegisterSuccess = {
                        navController.navigate("main") {
                            popUpTo("auth") { inclusive = true }
                        }
                    },
                    onBackToLogin = {
                        navController.popBackStack(AppDestinations.Login.route, false)
                    }
                )
            }
        }

        // Navigation for "inside" of the app content
        navigation(startDestination = AppDestinations.Home.route, route = "main") {
            composable(AppDestinations.Home.route) {
                MainScaffold(startDestination = AppDestinations.Home.route, parentNavController = navController)
            }

            composable(AppDestinations.Topics.route) {
                MainScaffold(startDestination = AppDestinations.Topics.route, parentNavController = navController)
            }

            // Add more main destinations here if needed
        }
    }
}


/**
 * MainScaffold manages the bottom bar and main in-app screens.
 */
@Composable
fun MainScaffold(
    startDestination: String,
    parentNavController: NavHostController
) {
    val innerNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = innerNavController,
                onItemClick = { destination ->
                    innerNavController.navigate(destination.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = innerNavController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppDestinations.Home.route) {
                HomeScreen()
            }

            composable(AppDestinations.Topics.route) {
                TopicListScreen(navController = innerNavController)
            }

            /*
            composable(AppDestinations.Profile.route) {
                ProfileScreen()
            }
            */
        }
    }
}

package com.student.mentalpotion.features.authentication.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.student.mentalpotion.R

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val splashState by viewModel.splashState.collectAsState()

    LaunchedEffect(splashState.navigateToMain) {
        if (splashState.navigateToMain) {
            navController.navigate("main") {
                popUpTo(0) { inclusive = true }
            }
            viewModel.resetState()
        }
    }

    LaunchedEffect(splashState.navigateToAuth) {
        if (splashState.navigateToAuth) {
            navController.navigate("auth") {
                popUpTo(0) { inclusive = true }
            }
            viewModel.resetState()
        }
    }

    // Visual splash while checking
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF221B30)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.default_profile),
                contentDescription = "Meowski Splash",
                modifier = Modifier.size(96.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            if (splashState.isLoading) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}

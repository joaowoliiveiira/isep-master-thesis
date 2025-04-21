package com.student.mentalpotion.features.authentication.presentation.login

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.student.mentalpotion.core.navigation.AppDestinations
import com.student.mentalpotion.ui.components.GradientButton
import com.student.mentalpotion.ui.components.PrivacySection
import com.student.mentalpotion.ui.theme.Purple161118
import com.student.mentalpotion.ui.theme.Purple525174

@Composable
fun LandingScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Purple525174, Purple161118)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 21.dp)
                .padding(bottom = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(23.dp)
            ) {
                GradientButton(
                    onClick = {
                        navController.navigate(AppDestinations.Login.route)
                    },
                    text = "Login",
                    contentDescription = "Login button",
                    modifier = Modifier.fillMaxWidth()
                )

                GradientButton(
                    onClick = {
                        navController.navigate(AppDestinations.Register.route)
                    },
                    text = "Register",
                    contentDescription = "Register button",
                    isSecondary = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(47.dp))

            PrivacySection(
                onPrivacyPolicyClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/"))
                    context.startActivity(intent)
                }
            )
        }
    }
}

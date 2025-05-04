package com.student.mentalpotion.features.authentication.presentation.login

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.student.mentalpotion.R
import com.student.mentalpotion.core.navigation.AppDestinations
import com.student.mentalpotion.ui.components.CustomButton
import com.student.mentalpotion.ui.theme.Black
import com.student.mentalpotion.ui.theme.White

@Composable
fun LandingScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background_1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Buttons Container
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 160.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomButton(
                text = "Login",
                onClick = {
                    navController.navigate(AppDestinations.Login.route)
                },
                isTransparent = true
            )

            CustomButton(
                text = "Register",
                onClick = {
                    navController.navigate(AppDestinations.Register.route)
                },
                isTransparent = false
            )
        }

        // Privacy Policy Text
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "By continuing, you accept our ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = White
                )

                ClickableText(
                    text = buildAnnotatedString {
                        pushStyle(
                            SpanStyle(
                                color = White,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                        append("Privacy Policy")
                    },
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/"))
                        context.startActivity(intent)
                              },
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

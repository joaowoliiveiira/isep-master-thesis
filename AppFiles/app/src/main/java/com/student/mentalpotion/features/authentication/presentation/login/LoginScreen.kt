package com.student.mentalpotion.features.authentication.presentation.login

import androidx.compose.material3.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Placeholder for Logo
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                contentDescription = "App Logo"
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Username & Password Fields
        OutlinedTextField(
            value = "", onValueChange = {},
            placeholder = { Text("Username") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "", onValueChange = {},
            placeholder = { Text("Password") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up Prompt
        Text("New User?", fontSize = 14.sp)
        Text("Sign Up", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Blue)

        Spacer(modifier = Modifier.height(16.dp))

        // Sign In Button
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.6f),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("Sign In", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text("or")
        Spacer(modifier = Modifier.height(8.dp))

        // Guest Mode Button
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.6f),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("Guest Mode", color = Color.White)
        }
    }
}
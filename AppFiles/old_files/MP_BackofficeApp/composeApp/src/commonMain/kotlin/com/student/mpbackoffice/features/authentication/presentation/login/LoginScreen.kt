package com.student.mpbackoffice.features.authentication.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 80.dp)
            .padding(vertical = 351.dp)
            .semantics { contentDescription = "Login Screen" },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .width(402.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            // Username Field
            Text(
                text = "Username",
                color = Color(0xFF475159),
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(73.dp)
                    .semantics { contentDescription = "Username input field" },
                placeholder = { Text("Enter your username") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF7D8C9A),
                    unfocusedBorderColor = Color(0xFFADBBC8),
                    focusedTextColor = Color(0xFF475159),
                    unfocusedTextColor = Color(0xFF475159)
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Field
            Text(
                text = "Password",
                color = Color(0xFF475159),
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .semantics { contentDescription = "Password input field" },
                placeholder = { Text("Enter your password") },
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF7D8C9A),
                    unfocusedBorderColor = Color(0xFFADBBC8),
                    focusedTextColor = Color(0xFF475159),
                    unfocusedTextColor = Color(0xFF475159)
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(44.dp))

            // Register Link
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "New User? ",
                    color = Color(0xFF2C3339),
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Register",
                    color = Color(0xFF2C3339),
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .semantics { contentDescription = "Register link" }
                        .clickable { onRegisterClick() },
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                )
            }

            Spacer(modifier = Modifier.height(29.dp))

            // Login Button
            Button(
                onClick = { onLoginClick(username, password) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(64.dp)
                    .width(259.dp)
                    .semantics { contentDescription = "Login button" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7D8C9A),
                    contentColor = Color.White
                ),
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Text(
                    text = "Login",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

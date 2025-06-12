package com.student.mentalpotion.features.authentication.presentation.login

import androidx.compose.material3.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.student.mentalpotion.core.navigation.AppDestinations
import com.student.mentalpotion.features.authentication.domain.model.AuthUser

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: (AuthUser) -> Unit,
    navController: NavController
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.loginState.collectAsState()

    LaunchedEffect(uiState.user) {
        uiState.user?.let {
            onLoginSuccess(it)
            viewModel.resetState()
        }
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.resetState()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = Color(0xFF7F0000),
                    contentColor = Color.White
                )
            }
        },
        containerColor = Color(0xFF161118)
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF161118))
                .padding(horizontal = 21.dp)
                .padding(top = 25.dp, bottom = 67.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Headline & Register
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Login",
                        fontSize = 30.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "New to MentalPotion?",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "Register",
                            fontSize = 16.sp,
                            color = Color.White,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable {
                                navController.navigate(AppDestinations.Register.route)
                            }
                        )
                    }
                }

                // Inputs
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    OutlinedTextField(
                        value = viewModel.email,
                        onValueChange = { viewModel.email = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("E-mail") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedTextColor = MaterialTheme.colorScheme.primary,
                            focusedTextColor = MaterialTheme.colorScheme.primary,
                            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    )

                    OutlinedTextField(
                        value = viewModel.password,
                        onValueChange = { viewModel.password = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Password") },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                            focusedTextColor = MaterialTheme.colorScheme.onBackground,
                            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    )

                    Text(
                        text = "Forgot your password?",
                        fontSize = 14.sp,
                        color = Color.White,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .clickable {
                                // TODO: Add navigation to ForgotPassword
                            }
                    )
                }

                // Login Button
                Button(
                    onClick = { viewModel.onLoginClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0x26D9D9D9),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Login", fontSize = 20.sp)
                }
            }

            if (uiState.isLoading) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
        }
    }
}

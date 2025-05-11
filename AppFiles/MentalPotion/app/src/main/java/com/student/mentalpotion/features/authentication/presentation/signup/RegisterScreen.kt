package com.student.mentalpotion.features.authentication.presentation.signup

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.student.mentalpotion.features.authentication.domain.model.User


@Composable
fun RegisterScreen(
    onRegisterSuccess: (User) -> Unit,
    onBackToLogin: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // If success: navigate and reset state
    LaunchedEffect(uiState) {
        if (uiState is RegisterUiState.Success) {
            onRegisterSuccess((uiState as RegisterUiState.Success).user)
            viewModel.resetState()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Register", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.register(email, password) },
            modifier = Modifier.fillMaxWidth(0.6f),
            enabled = uiState !is RegisterUiState.Loading
        ) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = onBackToLogin) {
            Text("Already have an account? Login")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Loading / Error
        when (uiState) {
            is RegisterUiState.Loading -> {
                CircularProgressIndicator()
            }

            is RegisterUiState.Error -> {
                Text(
                    text = (uiState as RegisterUiState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> {}
        }
    }
}
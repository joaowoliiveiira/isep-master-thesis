package com.student.mpbackoffice.features.authentication.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.student.mpbackoffice.core.presentation.*

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginClick: (String, String) -> Unit,
    onRegisterClick: (String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is LoginAction.OnLoginClick -> onLoginClick(action.email, action.password)
                is LoginAction.OnRegisterClick -> onRegisterClick(action.email)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 48.dp)
            .semantics { contentDescription = "Login Screen" },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            // Username Field
            Text(
                text = "Username",
                color = PrimaryText,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = state.username,
                onValueChange = { onAction(LoginAction.OnUsernameChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                placeholder = { Text("Enter your username") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryButton,
                    unfocusedBorderColor = BorderColor,
                    focusedTextColor = PrimaryText,
                    unfocusedTextColor = PrimaryText
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Field
            Text(
                text = "Password",
                color = PrimaryText,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = { onAction(LoginAction.OnPasswordChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                placeholder = { Text("Enter your password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryButton,
                    unfocusedBorderColor = BorderColor,
                    focusedTextColor = PrimaryText,
                    unfocusedTextColor = PrimaryText
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Error Message (if any)
            state.errorMeesage?.let { error ->
                Text(
                    text = error.asString(),
                    //color = MaterialTheme.colorScheme.error,
                    //style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Register link
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "New User? ", color = SecondaryText)
                Text(
                    text = "Register",
                    color = SecondaryText,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .clickable { onAction(LoginAction.OnRegisterClick(state.username)) }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Login Button or Loading
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = {
                        onAction(LoginAction.OnLoginClick(state.username, state.password))
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .height(56.dp)
                        .width(240.dp),
                    colors = ButtonDefaults.buttonColors(
                        //containerColor = PrimaryButton,
                        contentColor = White
                    )
                ) {
                    Text(text = "Login", fontSize = 18.sp)
                }
            }
        }
    }
}

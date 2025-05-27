package com.student.mpbackoffice.features.authentication.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.student.mpbackoffice.core.presentation.*
import com.student.mpbackoffice.features.authentication.presentation.components.AuthButton
import com.student.mpbackoffice.features.authentication.presentation.components.AuthTextField
import org.koin.compose.viewmodel.koinViewModel

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
private fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

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
            AuthTextField(
                label = "Username",
                value = state.email,
                onValueChange = { onAction(LoginAction.OnEmailChanged(it)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                label = "Password",
                value = state.password,
                onValueChange = { onAction(LoginAction.OnPasswordChanged(it)) },
                isPassword = true,
                imeAction = ImeAction.Done
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Error Message (if any)
            state.errorMessage?.let { error ->
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
                        .clickable { onAction(LoginAction.OnRegisterClick(state.email)) }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            AuthButton(
                isLoading = state.isLoading,
                onClick = {
                    onAction(LoginAction.OnLoginClick(state.email, state.password))
                }
            )
        }
    }
}
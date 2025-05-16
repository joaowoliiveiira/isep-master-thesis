package com.student.mpbackoffice.features.authentication.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.student.mpbackoffice.core.presentation.*
import com.student.mpbackoffice.features.authentication.presentation.components.AuthButton
import com.student.mpbackoffice.features.authentication.presentation.components.AuthTextField

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
            AuthTextField(
                label = "Username",
                value = state.username,
                onValueChange = { onAction(LoginAction.OnUsernameChanged(it)) }
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

            AuthButton(
                isLoading = state.isLoading,
                onClick = {
                    onAction(LoginAction.OnLoginClick(state.username, state.password))
                }
            )
        }
    }
}
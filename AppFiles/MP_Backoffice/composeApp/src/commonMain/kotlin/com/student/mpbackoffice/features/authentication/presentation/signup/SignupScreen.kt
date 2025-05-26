package com.student.mpbackoffice.features.authentication.presentation.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
fun SignupScreenRoot(
    viewModel: SignupViewModel = koinViewModel(),
    onSignupSuccess: () -> Unit,
    onBackToLogin: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SignupScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is SignupAction.OnSignupClick -> {
                    viewModel.onAction(action)
                    onSignupSuccess()
                }
                is SignupAction.OnBackToLoginClick -> {
                    onBackToLogin()
                }
                else -> viewModel.onAction(action)
            }
        }
    )
}


@Composable
fun SignupScreen(
    state: SignupState,
    onAction: (SignupAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.widthIn(max = 400.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            AuthTextField(
                label = "Email",
                value = state.email,
                onValueChange = { onAction(SignupAction.OnEmailChanged(it)) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthTextField(
                label = "Password",
                value = state.password,
                onValueChange = { onAction(SignupAction.OnPasswordChanged(it)) },
                isPassword = true
            )
            Spacer(modifier = Modifier.height(24.dp))

            state.errorMessage?.let { error ->
                Text(text = error.asString(), modifier = Modifier.padding(bottom = 8.dp))
            }

            AuthButton(
                isLoading = state.isLoading,
                onClick = {
                    onAction(SignupAction.OnSignupClick(state.email, state.password))
                }
            )
        }
    }
}

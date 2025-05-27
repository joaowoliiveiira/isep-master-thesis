package com.student.mpbackoffice.features.authentication.presentation.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
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
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    SignupScreen(
        state = state,
        effect = effect,
        onAction = viewModel::onAction,
        onSignupSuccess = onSignupSuccess,
        onBackToLogin = onBackToLogin
    )
}

@Composable
fun SignupScreen(
    state: SignupState,
    effect: SignupEffect?,
    onAction: (SignupAction) -> Unit,
    onSignupSuccess: () -> Unit,
    onBackToLogin: () -> Unit
) {
    // Handle one-time effects (navigation, etc.)
    LaunchedEffect(effect) {
        when (effect) {
            is SignupEffect.SignupSuccess -> onSignupSuccess()
            is SignupEffect.NavigateBackToLogin -> onBackToLogin()
            null -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 48.dp),
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
                label = "Email",
                value = state.email,
                onValueChange = { onAction(SignupAction.OnEmailChanged(it)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                label = "Password",
                value = state.password,
                onValueChange = { onAction(SignupAction.OnPasswordChanged(it)) },
                isPassword = true,
                imeAction = ImeAction.Done
            )

            Spacer(modifier = Modifier.height(24.dp))

            state.errorMessage?.let { error ->
                Text(
                    text = error.asString(),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            AuthButton(
                isLoading = state.isLoading,
                onClick = {
                    onAction(SignupAction.OnSignupClick(state.email, state.password))
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Already have an account? ", color = SecondaryText)
                Text(
                    text = "Login",
                    color = SecondaryText,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        onAction(SignupAction.OnBackToLoginClick(state.email))
                    }
                )
            }
        }
    }
}
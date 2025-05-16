package com.student.mpbackoffice

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.student.mpbackoffice.features.authentication.presentation.components.AuthButton
import com.student.mpbackoffice.features.authentication.presentation.login.LoginScreenRoot
import com.student.mpbackoffice.features.authentication.presentation.login.LoginViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import mp_backoffice.composeapp.generated.resources.Res
import mp_backoffice.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        /*
        LoginScreenRoot(
            viewModel = remember { LoginViewModel() },
            onLoginClick = { } as (String, String) -> Unit,
            onRegisterClick = { }
        )*/
        AuthButton(
            isLoading = true,
            onClick = {},
        )
    }
}
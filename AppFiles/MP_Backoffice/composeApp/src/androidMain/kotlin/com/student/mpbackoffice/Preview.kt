package com.student.mpbackoffice

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.student.mpbackoffice.features.authentication.presentation.components.AuthButton
import com.student.mpbackoffice.features.authentication.presentation.components.AuthTextField

@Preview
@Composable
private fun AuthTextFieldPreview() {
    MaterialTheme {
        AuthTextField(onValueChange = {})
    }
}

@Preview
@Composable
private fun AuthButtonPreview() {
    MaterialTheme {
        AuthButton(
            isLoading = false,
            onClick = {}
        )
    }
}
package com.student.mpbackoffice.features.authentication.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.student.mpbackoffice.core.presentation.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AuthButton(
    isLoading: Boolean,
    onClick: () -> Unit
) {
    if (isLoading) {
        CircularProgressIndicator()
    } else {
        Button(
            onClick = onClick,
            modifier = Modifier
                //.align(Alignment.CenterHorizontally)
                .height(56.dp)
                .width(240.dp),
            colors = ButtonDefaults.buttonColors(contentColor = White)
        ) {
            Text(text = "Login", fontSize = 18.sp)
        }
    }
}
package com.student.mentalpotion.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GradientButton(
    onClick: () -> Unit,
    text: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    isSecondary: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(65.dp)
            .semantics {
                this.contentDescription = contentDescription
                this.role = Role.Button
            },
        colors = ButtonDefaults.buttonColors(

        ),
        shape = RoundedCornerShape(10.dp),
        border = if (isSecondary) ButtonDefaults.outlinedButtonBorder else null,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()

                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                textAlign = TextAlign.Center,
            )
        }
    }
}

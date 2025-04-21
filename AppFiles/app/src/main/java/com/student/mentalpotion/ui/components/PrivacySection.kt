package com.student.mentalpotion.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun PrivacySection(
    onPrivacyPolicyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "By continuing, you accept our",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            textAlign = TextAlign.Center
        )

        TextButton(
            onClick = onPrivacyPolicyClick,
            modifier = Modifier
                .semantics {
                    contentDescription = "Privacy Policy link"
                    role = Role.Button
                }
        ) {
            Text(
                text = "Privacy Policy",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

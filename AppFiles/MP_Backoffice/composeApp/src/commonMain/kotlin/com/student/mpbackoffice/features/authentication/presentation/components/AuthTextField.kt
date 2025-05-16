package com.student.mpbackoffice.features.authentication.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.student.mpbackoffice.core.presentation.*

@Composable
fun AuthTextField(
    label: String = "label",
    value: String = "",
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier
) {
    Text(
        text = label,
        color = PrimaryText,
        fontSize = 14.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        placeholder = { Text("Enter your $label") },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text,
            imeAction = imeAction
        ),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryButton,
            unfocusedBorderColor = BorderColor,
            focusedTextColor = PrimaryText,
            unfocusedTextColor = PrimaryText
        )
    )
}

package com.student.mentalpotion.features.authentication.presentation.login

import androidx.compose.material3.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.student.mentalpotion.features.authentication.domain.model.User
import com.student.mentalpotion.core.navigation.AppDestinations
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    onLoginSuccess: (User) -> Unit,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState = viewModel.loginState

    LaunchedEffect(loginState) {
        if (loginState is LoginUiState.Success) {
            onLoginSuccess(loginState.user)
            viewModel.resetUiState()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF161118))
            .padding(horizontal = 21.dp)
            .padding(top = 25.dp, bottom = 67.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Login",
                    fontSize = 30.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "New to MentalPotion?",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Text(
                        text = "Register",
                        fontSize = 16.sp,
                        color = Color.White,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            navController.navigate(AppDestinations.Register.route)
                        }
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.email = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("E-mail") },
                    /*
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color(0xFFD9D9D9)
                    ),*/
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )

                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    /*
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color(0xFFD9D9D9)
                    ),*/
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true
                )

                Text(
                    text = "Forgot your password?",
                    fontSize = 14.sp,
                    color = Color.White,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .clickable {
                            // TODO: Navigate to ForgotPassword screen later
                        }
                )
            }

            Button(
                onClick = { viewModel.onLoginClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0x26D9D9D9),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Login", fontSize = 20.sp)
            }
        }

        if (loginState is LoginUiState.Loading) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }

        if (loginState is LoginUiState.Error) {
            LaunchedEffect(Unit) {
                delay(100)
                SnackbarHostState().showSnackbar(loginState.message)
                viewModel.resetUiState()
            }
        }
    }
}
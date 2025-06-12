package com.student.mentalpotion.features.authentication.presentation.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.student.mentalpotion.R
import com.student.mentalpotion.core.domain.model.RegisteredUser
import kotlinx.coroutines.delay

private const val MessageDelay = 400L
private const val NextStepDelay = 600L

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onRegisterSuccess: (RegisteredUser) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    var step by remember { mutableStateOf(RegisterStep.Welcome) }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val uiState by viewModel.registerState.collectAsState()

    LaunchedEffect(uiState.user) {
        uiState.user?.let {
            onRegisterSuccess(it)
            viewModel.resetState()
        }
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.resetState()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = Color(0xFF7F0000),
                    contentColor = Color.White
                )
            }
        },
        containerColor = Color(0xFF221B30)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AvatarWithName()
            Spacer(modifier = Modifier.height(24.dp))

            AnimatedStep(step, email, username, password,
                onEmailEntered = {
                    email = it
                    step = RegisterStep.AskUsername
                },
                onUsernameEntered = {
                    username = it
                    step = RegisterStep.AskPassword
                },
                onPasswordEntered = {
                    password = it
                    viewModel.register(email, password, username)
                }
            )

            if (uiState.isLoading) {
                Spacer(modifier = Modifier.height(24.dp))
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}


@Composable
fun AnimatedStep(
    step: RegisterStep,
    email: String,
    username: String,
    password: String,
    onEmailEntered: (String) -> Unit,
    onUsernameEntered: (String) -> Unit,
    onPasswordEntered: (String) -> Unit
) {
    when (step) {
        RegisterStep.Welcome -> {
            AnimatedMessage("Hello! I'm Meowski. Welcome to MentalPotion!") {
                onEmailEntered("") // advances flow
            }
        }

        RegisterStep.AskEmail -> {
            AnimatedMessage("Let’s get your account set up. What’s your email?")
            AnimatedInputField("Email", onNext = onEmailEntered)
        }

        RegisterStep.AskUsername -> {
            AnimatedMessage("Nice! And what should we call you?")
            AnimatedInputField("Username", onNext = onUsernameEntered)
        }

        RegisterStep.AskPassword -> {
            AnimatedMessage("Last step! Choose a secure password.")
            AnimatedInputField("Password", password = true, onNext = onPasswordEntered)
        }
    }
}


@Composable
fun AnimatedMessage(text: String, onShown: (() -> Unit)? = null) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(MessageDelay)
        visible = true
        delay(NextStepDelay)
        onShown?.invoke()
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it / 4 })
    ) {
        ChatBubble(text)
    }
}

@Composable
fun AnimatedInputField(
    label: String,
    password: Boolean = false,
    onNext: (String) -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        delay(NextStepDelay)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it / 4 })
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text(label) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                singleLine = true,
                visualTransformation = if (password) PasswordVisualTransformation() else VisualTransformation.None
            )
            Button(
                onClick = { onNext(text) },
                modifier = Modifier.padding(top = 12.dp),
                enabled = text.isNotBlank()
            ) {
                Text("Next")
            }
        }
    }
}


enum class RegisterStep {
    Welcome, AskEmail, AskUsername, AskPassword
}

@Composable
fun AvatarWithName() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.default_profile),
            contentDescription = "Meowski",
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            color = Color.Black,
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .defaultMinSize(minHeight = 32.dp)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Meowski",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun ChatBubble(text: String, isUser: Boolean = false) {
    Surface(
        color = Color(0xFF5D4A43),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFFFD7A5)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (isUser) 48.dp else 0.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun OptionButton(label: String) {
    OutlinedButton(
        onClick = { /* TODO */ },
        modifier = Modifier
            //.weight(1f)
            .height(50.dp),
        border = BorderStroke(2.dp, Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = label, color = Color.White, fontSize = 16.sp)
    }
}

@Composable
fun AgreementScale(selectedIndex: Int = 4) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Agree", color = Color(0xFFFFD466))
            Text("Disagree", color = Color(0xFFFFD466))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(5) { index ->
                val isSelected = index == selectedIndex
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) Color(0xFFA48AE1)
                            else Color.Transparent,
                            CircleShape
                        )
                        .border(2.dp, Color(0xFFFFD466), CircleShape)
                )
            }
        }
    }
}
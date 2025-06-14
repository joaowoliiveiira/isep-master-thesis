package com.student.mentalpotion.features.authentication.presentation.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import com.student.mentalpotion.core.util.ApiError
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.ui.components.AvatarPickerDialog
import kotlinx.coroutines.delay

private const val MessageDelay = 400L
private const val NextStepDelay = 600L

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onRegisterSuccess: (RegisteredUser) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    var step by remember { mutableStateOf(RegisterStep.AskEmail) }

    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailInput by remember { mutableStateOf("") }
    var usernameInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }


    var inputEnabled by remember { mutableStateOf(true) }

    val uiState by viewModel.registerState.collectAsState()
    val messages = remember { mutableStateListOf<@Composable () -> Unit>() }

    // On successful registration
    LaunchedEffect(uiState.user) {
        uiState.user?.let {
            onRegisterSuccess(it)
            viewModel.resetState()
        }
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let { errorMessage ->
            inputEnabled = true

            if (errorMessage.error == ApiError.EmailAlreadyInUse) {
                messages.add {
                    ChatBubble("Hmm, looks like this email is already in use. Try a different one.")
                }
                step = RegisterStep.AskEmail
            } else {
                snackbarHostState.showSnackbar(errorMessage.message)
            }

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

            // Show chat history
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(messages.size) { index ->
                    messages[index].invoke()
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            // Input flow logic
            AnimatedStep(
                step = step,
                email = email,
                username = username,
                password = password,
                emailInput = emailInput,
                onEmailInputChange = { emailInput = it },
                usernameInput = usernameInput,
                onUsernameInputChange = { usernameInput = it },
                passwordInput = passwordInput,
                onPasswordInputChange = { passwordInput = it },
                messages = messages,
                onEmailEntered = { input ->
                    email = input
                    messages.add { ChatBubble(input, isUser = true) }
                    step = RegisterStep.AskUsername
                },
                onUsernameEntered = { input ->
                    username = input
                    messages.add { ChatBubble(input, isUser = true) }
                    step = RegisterStep.AskPassword
                },
                onPasswordEntered = { input ->
                    password = input
                    messages.add { ChatBubble("••••••", isUser = true) }
                    step = RegisterStep.AskAvatar
                },
                onAvatarEntered = { avatarId ->
                    messages.add { ChatBubble("✅ Avatar selected: $avatarId", isUser = true) }
                    viewModel.register(email, password, username, avatarId)
                }
            )


            if (uiState.isLoading) {
                Spacer(modifier = Modifier.height(24.dp))
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}


enum class RegisterStep {
    AskEmail, AskUsername, AskPassword, AskAvatar
}

@Composable
fun AnimatedStep(
    step: RegisterStep,
    email: String,
    username: String,
    password: String,
    onEmailEntered: (String) -> Unit,
    onUsernameEntered: (String) -> Unit,
    onPasswordEntered: (String) -> Unit,
    onAvatarEntered: (String) -> Unit,
    messages: SnapshotStateList<@Composable () -> Unit>,
    emailInput: String,
    onEmailInputChange: (String) -> Unit,
    usernameInput: String,
    onUsernameInputChange: (String) -> Unit,
    passwordInput: String,
    onPasswordInputChange: (String) -> Unit,
) {
    when (step) {
        RegisterStep.AskEmail -> {
            LaunchedEffect(Unit) {
                delay(MessageDelay)
                messages.add { ChatBubble("Hello! I'm Meowski. Welcome to MentalPotion!") }
                delay(NextStepDelay)
                messages.add { ChatBubble("Let’s get your account set up. What’s your email?") }
            }
            AnimatedInputField(
                label = "Email",
                text = emailInput,
                onTextChange = onEmailInputChange,
                onNext = {
                    onEmailEntered(emailInput)
                }
            )
        }

        RegisterStep.AskUsername -> {
            LaunchedEffect(Unit) {
                delay(NextStepDelay)
                messages.add { ChatBubble("Nice! And what should we call you?") }
            }
            AnimatedInputField(
                label = "Username",
                text = usernameInput,
                onTextChange = onUsernameInputChange,
                onNext = {
                    onUsernameEntered(usernameInput)
                }
            )
        }

        RegisterStep.AskPassword -> {
            LaunchedEffect(Unit) {
                delay(NextStepDelay)
                messages.add { ChatBubble("Last step! Choose a secure password.") }
            }
            AnimatedInputField(
                label = "Password",
                password = true,
                text = passwordInput,
                onTextChange = onPasswordInputChange,
                onNext = {
                    onPasswordEntered(passwordInput)
                }
            )
        }

        RegisterStep.AskAvatar -> {
            var showDialog by remember { mutableStateOf(true) }

            LaunchedEffect(Unit) {
                delay(NextStepDelay)
                messages.add { ChatBubble("Looking good! Pick an avatar to represent you.") }
            }

            if (showDialog) {
                AvatarPickerDialog(
                    onDismiss = { showDialog = false },
                    onAvatarSelected = { avatarId ->
                        showDialog = false
                        messages.add {
                            ChatBubble("Awesome choice!", isUser = false)
                        }
                        onAvatarEntered(avatarId)
                    }
                )
            }
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
    text: String,
    onTextChange: (String) -> Unit,
    password: Boolean = false,
    onNext: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(label) {
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
                onValueChange = onTextChange,
                label = { Text(label) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                singleLine = true,
                isError = label == "Email" && text.isNotBlank() && !text.contains("@"),
                visualTransformation = if (password) PasswordVisualTransformation() else VisualTransformation.None
            )
            Button(
                onClick = onNext,
                modifier = Modifier.padding(top = 12.dp),
                enabled = text.isNotBlank() && (label != "Email" || text.contains("@"))
            ) {
                Text("Next")
            }
        }
    }
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
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = if (isUser) Color(0xFF6A5ACD) else Color(0xFF5D4A43),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFFFFD7A5)),
            modifier = Modifier
                .widthIn(max = 300.dp)
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
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
package com.student.mpbackoffice.features.data_manager.presentation.home


import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.student.mpbackoffice.features.authentication.domain.repository.AuthRepository
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    authRepository: AuthRepository = koinInject()
) {
    val currentUser = authRepository.getCurrentUser()
    val email = currentUser?.email ?: "Unknown User"

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("🏠 Welcome to the Home Page!")
            Spacer(modifier = Modifier.height(8.dp))
            Text("You are logged in as: $email")
        }
    }
}
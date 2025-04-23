package com.student.mentalpotion.features.home.presentation.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.student.mentalpotion.ui.theme.*

@Composable
fun HomeScreen() {
    var activeTab by remember { mutableStateOf("overall") }
    val selectedDays = remember { mutableStateOf(setOf<String>()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Background Image
        Image(
            painter = rememberAsyncImagePainter("https://cdn.builder.io/api/v1/image/assets/afcabc172fef4306a6dd382c946ab92b/5985508b611f2b92a82c48caa43d0a7c1363d88d?placeholderIfAbsent=true"),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TopBar()

            Spacer(modifier = Modifier.height(16.dp))

            TabSection(
                activeTab = activeTab,
                onTabSelected = { activeTab = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (activeTab) {
                "overall" -> OverallContent(selectedDays)
                // "daily" -> DailyRoutineContent() // (Future)
            }
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Info
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://images.pexels.com/photos/2381069/pexels-photo-2381069.jpeg"),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
                    .clickable { /* TODO: Go to profile */ }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "João Oliveira",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .background(
                        TransparentDark,
                        RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }

        // Crisis and Notifications
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                painter = rememberAsyncImagePainter("https://images.pexels.com/photos/7821343/pexels-photo-7821343.jpeg"),
                contentDescription = "Notifications",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { /* TODO: Notifications */ }
            )

            Button(
                onClick = { /* TODO: Crisis call */ },
                colors = ButtonDefaults.buttonColors(containerColor = CrisisRed),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Text(
                    "Crisis",
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
private fun TabSection(activeTab: String, onTabSelected: (String) -> Unit) {
    Surface(
        color = TransparentDark,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TabButton(
                title = "Overall View",
                selected = activeTab == "overall",
                onClick = { onTabSelected("overall") }
            )
            TabButton(
                title = "Daily Routine",
                selected = activeTab == "daily",
                onClick = { onTabSelected("daily") }
            )
        }
    }
}

@Composable
private fun TabButton(title: String, selected: Boolean, onClick: () -> Unit) {
    Text(
        text = title,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        color = if (selected) GoldenText else SubtleGold,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun OverallContent(selectedDays: MutableState<Set<String>>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ActivityCard()
        WeeklyRoutine(selectedDays)
        FavoriteToolsSection()
    }
}

@Composable
private fun ActivityCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = DarkBackground.copy(alpha = 0.9f)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { /* TODO: Go to Activity */ }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Next Activity...",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
            Icon(
                painter = rememberAsyncImagePainter("https://images.pexels.com/photos/4843911/pexels-photo-4843911.jpeg"),
                contentDescription = "Activity Icon",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun WeeklyRoutine(selectedDays: MutableState<Set<String>>) {
    Card(
        colors = CardDefaults.cardColors(containerColor = DarkBackground.copy(alpha = 0.9f)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                "Weekly Routine",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                listOf("M", "T", "W", "T", "F", "S", "S").forEach { day ->
                    DayCircle(
                        label = day,
                        selected = selectedDays.value.contains(day),
                        onSelect = {
                            selectedDays.value = selectedDays.value.toggle(day)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun DayCircle(label: String, selected: Boolean, onSelect: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .border(2.dp, SubtleGold, CircleShape)
            .background(if (selected) Color.Gray.copy(alpha = 0.5f) else Color.Transparent, CircleShape)
            .clickable { onSelect() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = SubtleGold,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun FavoriteToolsSection() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .border(2.dp, BorderColor, RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                "Favourite Tools",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .background(DarkBackground.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
                            .border(2.dp, BorderColor, RoundedCornerShape(12.dp))
                            .clickable { /* TODO: Go to Tool Detail */ }
                    )
                }
            }
        }
    }
}

/**
 * Extension function to toggle item in Set
  */
private fun Set<String>.toggle(item: String): Set<String> {
    return if (contains(item)) minus(item) else plus(item)
}
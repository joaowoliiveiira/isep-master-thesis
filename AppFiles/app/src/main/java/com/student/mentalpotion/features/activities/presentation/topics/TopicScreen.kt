package com.student.mentalpotion.features.activities.presentation.topics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.student.mentalpotion.R

@Composable
fun TopicScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Background
        Image(
            painter = rememberAsyncImagePainter("YOUR_BG_IMAGE_URL"),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // 1. Header
            Header()

            Spacer(modifier = Modifier.height(15.dp))

            // 2. Mascot + Speech
            MascotSpeech()

            Spacer(modifier = Modifier.height(20.dp))

            // 3. Difficulty Section
            DifficultySection()

            Spacer(modifier = Modifier.height(20.dp))

            // 4. Resources Section
            ResourcesSection()
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Sleep",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Surface(
            shape = CircleShape,
            color = Color(0xFF7F0000)
        ) {
            Text(
                text = "Crisis",
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp),
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun MascotSpeech() {
    Row(verticalAlignment = Alignment.Top) {
        Image(
            painter = rememberAsyncImagePainter("YOUR_MASCOT_URL"),
            contentDescription = "Mascot",
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SpeechBubble("Sleep plays a vital role...")
            SpeechBubble("Here you can discover tips...")
        }
    }
}

@Composable
fun SpeechBubble(text: String) {
    Surface(
        color = Color(0xFFEDEDED),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(10.dp),
            color = Color.Black,
            fontSize = 14.sp
        )
    }
}

@Composable
fun DifficultySection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Difficulty",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .background(Color.Black)
                .border(1.dp, Color.White, RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 6.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DifficultyCard("Beginner", Color(0xFF8C33FF))
            DifficultyCard("Average", Color.Gray)
            DifficultyCard("Advanced", Color.DarkGray)
        }
    }
}

@Composable
fun DifficultyCard(label: String, bgColor: Color) {
    Surface(
        color = bgColor,
        modifier = Modifier
            .width(90.dp)
            .height(140.dp)
            .clickable { /* Handle click */ },
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "$label\nLevel\nSleep Routine",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun ResourcesSection() {
    Column {
        Text(
            text = "Resources",
            modifier = Modifier
                .background(Color.Black)
                .border(1.dp, Color.White, RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 6.dp),
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(10.dp))

        var selectedTab by remember { mutableStateOf(0) }
        val tabs = listOf("Sleep sounds", "Sleep stories", "Sleep meditation")

        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.Transparent,
            contentColor = Color.White
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(text = title) }
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(10) { index ->
                ResourceCard("Sound $index")
            }
        }
    }
}

@Composable
fun ResourceCard(label: String) {
    Surface(
        modifier = Modifier
            .width(120.dp)
            .height(120.dp),
        shape = RoundedCornerShape(10.dp),
        color = Color(0xFF161118),
        border = BorderStroke(1.dp, Color.White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(
                painter = painterResource(id = R.drawable.background_2),
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
            Text(label, color = Color.White, fontSize = 14.sp)
        }
    }
}

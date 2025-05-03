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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import androidx.hilt.navigation.compose.hiltViewModel

import com.student.mentalpotion.R

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val selectedTab by viewModel.selectedTab.collectAsState()
    val selectedTool by viewModel.selectedTool.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF161118))
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.screenshot_32),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileHeader()

            TabNavigation(
                selectedTab = selectedTab,
                onTabSelected = viewModel::setSelectedTab
            )

            when (selectedTab) {
                "overall" -> OverallContent(
                    selectedTool = selectedTool,
                    onToolSelected = viewModel::setSelectedTool
                )
                "daily" -> DailyContent()
            }
        }
    }
}

@Composable
private fun ProfileHeader() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        color = Color(0xFF161118).copy(alpha = 0.7f),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        "https://images.pexels.com/photos/2381069/pexels-photo-2381069.jpeg"
                    ),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = "João Oliveira",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Button(
                onClick = { /* TODO: Crisis Button Action */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7F0000)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Crisis",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun TabNavigation(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.7f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TabButton(
                text = "Overall View",
                selected = selectedTab == "overall",
                onClick = { onTabSelected("overall") }
            )
            TabButton(
                text = "Daily routine",
                selected = selectedTab == "daily",
                onClick = { onTabSelected("daily") }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color(0xFFCAA982).copy(alpha = 0.3f))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(2.dp)
                    .background(Color(0xFFE8C59C))
                    .align(if (selectedTab == "overall") Alignment.CenterStart else Alignment.CenterEnd)
            )
        }
    }
}

@Composable
private fun TabButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            color = if (selected) Color(0xFFE8C59C) else Color(0xFFCAA982),
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun OverallContent(
    selectedTool: Int?,
    onToolSelected: (Int?) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NextActivityCard()

        Spacer(modifier = Modifier.height(29.dp))

        WeeklyRoutineCard()

        Spacer(modifier = Modifier.height(32.dp))

        FavoriteToolsCard(
            selectedTool = selectedTool,
            onToolSelected = onToolSelected
        )
    }
}

@Composable
private fun NextActivityCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clickable { /* TODO */ },
        shape = RoundedCornerShape(10.dp),
        color = Color(0xFF161118).copy(alpha = 0.9f),
        border = BorderStroke(2.dp, Color(0xFFD9D9D9))
    ) {
        Box(
            modifier = Modifier.padding(
                start = 37.dp,
                end = 16.dp,
                top = 29.dp,
                bottom = 28.dp
            )
        ) {
            Text(
                text = "Next Activity...",
                color = Color.White,
                fontSize = 17.sp
            )
            Image(
                painter = painterResource(id = R.drawable.screenshot_32),
                contentDescription = "Next",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(12.dp)
                    .padding(top = 14.dp)
            )
        }
    }
}

@Composable
private fun WeeklyRoutineCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clickable { /* TODO */ },
        shape = RoundedCornerShape(10.dp),
        color = Color(0xFF161118).copy(alpha = 0.9f),
        border = BorderStroke(2.dp, Color(0xFFD9D9D9))
    ) {
        Column(
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "Weekly routine",
                color = Color.White,
                fontSize = 17.sp
            )

            Spacer(modifier = Modifier.height(54.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                repeat(7) {
                    Box(
                        modifier = Modifier
                            .size(33.dp)
                            .border(2.dp, Color(0xFFCAA982), CircleShape)
                    )
                }
            }
        }
    }
}

@Composable
private fun FavoriteToolsCard(
    selectedTool: Int?,
    onToolSelected: (Int?) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(10.dp),
        color = Color.Transparent,
        border = BorderStroke(2.dp, Color(0xFFD9D9D9))
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 13.dp,
                vertical = 27.dp
            )
        ) {
            Text(
                text = "Favourite Tools",
                color = Color.White,
                fontSize = 17.sp,
                modifier = Modifier.padding(start = 21.dp)
            )

            Spacer(modifier = Modifier.height(46.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .background(
                                if (selectedTool == index) Color(0xFF201B22).copy(alpha = 0.9f)
                                else Color(0xFF161118).copy(alpha = 0.6f),
                                RoundedCornerShape(10.dp)
                            )
                            .border(
                                2.dp,
                                if (selectedTool == index) Color(0xFFE8C59C)
                                else Color(0xFFD9D9D9),
                                RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                onToolSelected(if (selectedTool == index) null else index)
                            }
                    )
                }
            }
        }
    }
}

@Composable
private fun DailyContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Daily Routine Content Here",
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}
package com.student.mentalpotion.features.profile.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.student.mentalpotion.R

@Preview
@Composable
fun ProfilePageV3() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background
        Image(
            painter = painterResource(id = R.drawable.background_2),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Content
        Column(modifier = Modifier.fillMaxSize()) {
            ProfileSection()
            SchoolProgressSection()
            Spacer(modifier = Modifier.height(16.dp))
            MoodSummarySection()
        }
    }
}

@Preview
@Composable
private fun ProfileSection() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .zIndex(1f),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            color = Color(0xFF161118),
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileHeader()
                UsernameBadge("Username")
                Divider(
                    modifier = Modifier
                        .padding(vertical = 18.dp)
                        .width(328.dp)
                        .height(2.dp),
                    color = Color(0x99C1CCD6)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProfileHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 35.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.default_profile),
            contentDescription = "Profile Icon",
            modifier = Modifier.size(40.dp)
        )

        Box(
            modifier = Modifier
                .size(94.dp)
                .border(2.dp, Color.White, CircleShape)
                .background(Color.Black, CircleShape)
        )

        CrisisButton()
    }
}

@Preview
@Composable
private fun CrisisButton() {
    Surface(
        shape = RoundedCornerShape(90.dp),
        color = Color(0xFF7F0000)
    ) {
        Text(
            text = "Crisis",
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 14.dp)
        )
    }
}

@Composable
private fun UsernameBadge(name: String) {
    Surface(
        shape = RoundedCornerShape(90.dp),
        color = Color(0x80000000)
    ) {
        Text(
            text = name,
            color = Color.White,
            fontSize = 17.sp,
            modifier = Modifier.padding(horizontal = 55.dp, vertical = 12.dp)
        )
    }
}

@Preview
@Composable
private fun SchoolProgressSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0x66161118))
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "School of Sleep",
            color = Color.White,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )

        Box(
            modifier = Modifier
                .padding(top = 9.dp)
                .border(1.dp, Color.White, RoundedCornerShape(90.dp))
                .background(Color(0x80000000), RoundedCornerShape(90.dp))
                .padding(1.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(145.dp)
                    .height(15.dp)
                    .background(Color(0xE6FFFFFF), RoundedCornerShape(90.dp))
            )
        }

        Image(
            painter = painterResource(id = R.drawable.default_profile),
            contentDescription = "School Icon",
            modifier = Modifier
                .size(129.dp)
                .padding(top = 25.dp)
        )
    }
}

@Preview
@Composable
private fun MoodSummarySection() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF161118)),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 19.dp)
        ) {
            Divider(
                modifier = Modifier
                    .width(328.dp)
                    .height(2.dp),
                color = Color(0x99C1CCD6)
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                shape = RoundedCornerShape(10.dp),
                color = Color(0xCC161118)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Mood Summary",
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium
                    )

                    MoodGraph()

                    Text(
                        text = "3 4 5 6 7 8 9 10 11",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(top = 16.dp, start = 30.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MoodGraph() {
    Row(
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        // Circles
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            repeat(5) {
                Box(
                    modifier = Modifier
                        .size(33.dp)
                        .border(2.dp, Color(0xFFCAA982), CircleShape)
                )
            }
        }

        // Horizontal lines
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 26.dp),
            verticalArrangement = Arrangement.spacedBy(41.dp)
        ) {
            repeat(5) {
                Divider(
                    color = Color(0x66C1CCD6),
                    thickness = 2.dp
                )
            }
            Divider(
                modifier = Modifier.padding(top = 24.dp),
                color = Color(0xFFC1CCD6),
                thickness = 2.dp
            )
        }
    }
}

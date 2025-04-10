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

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

        /*
        // Banner Image
        Image(
            painter = painterResource(id = android.R.drawable.screen_background_dark),
            contentDescription = "Profile Banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )*/

        // Top Right Crisis Button & Settings Icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Crisis", color = Color.White, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_manage),
                    contentDescription = "Settings",
                    tint = Color.White
                )
            }
        }

        // Profile Picture
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Offset for the banner
            Spacer(modifier = Modifier.height(100.dp))
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_camera),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
        }
    }
}

@Composable
fun TopBar(
    name: String,
    modifier: Modifier = Modifier
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }
}

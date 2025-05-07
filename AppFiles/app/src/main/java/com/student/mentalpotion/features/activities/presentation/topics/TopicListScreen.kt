package com.student.mentalpotion.features.activities.presentation.topics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@Composable
fun TopicListScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://cdn.builder.io/api/v1/image/assets/163a94d9e33d457a877ad631aac14c7e/51601bb1a616ecfd72dc4f767e0d14dec72bd9a2"),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            // Header Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = Color.Black.copy(alpha = 0.5f),
                    shape = CircleShape,
                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.4f))
                ) {
                    Text(
                        text = "Topics",
                        modifier = Modifier.padding(horizontal = 65.dp, vertical = 9.dp),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Surface(
                    color = Color(0xFF7F0000),
                    shape = CircleShape,
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(
                        text = "Crisis",
                        modifier = Modifier.padding(horizontal = 18.dp, vertical = 14.dp),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.72.sp
                    )
                }
            }

            // Search Bar
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 26.dp)
                    .shadow(15.dp),
                shape = RoundedCornerShape(10.dp),
                color = Color.White.copy(alpha = 0.9f),
                border = BorderStroke(2.dp, Color.White)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 21.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberAsyncImagePainter("https://cdn.builder.io/api/v1/image/assets/163a94d9e33d457a877ad631aac14c7e/28b70ceda1d999fd6ac3b953d33529a8a5e296f3"),
                        contentDescription = "Search",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Search",
                        color = Color(0xFF161118),
                        fontSize = 20.sp,
                        letterSpacing = 0.1.sp
                    )
                }
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp)
                    .height(2.dp),
                color = Color(0xFFC1CCD6)
            )

            // Scrollable Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize() // takes the remaining space
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                /*
                val topics = null
                items(topics.size) { index ->
                    val topic = topics[index]
                    ActivityCard(
                        title = topic.title,
                        imageUrl = topic.imageUrl
                    )
                }*/
            }
        }
    }
}

@Composable
private fun ActivityCard(
    title: String,
    imageUrl: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(7.dp),
        shape = RoundedCornerShape(10.dp),
        color = Color(0xFF161118).copy(alpha = 0.8f),
        border = BorderStroke(2.dp, Color(0xFFCAA982))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 37.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = title,
                modifier = Modifier
                    .size(90.dp)
                    .padding(top = 23.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
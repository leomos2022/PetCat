@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.firucat.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firucat.ui.components.SafeAsyncImage
import com.example.firucat.ui.components.VideoPlayer

data class PetVideo(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val videoUrl: String
)

@Composable
fun PetScreen(navController: NavController) {
    var selectedVideo by remember { mutableStateOf<PetVideo?>(null) }
    
    val videos = listOf(
        PetVideo(
            1,
            "Playful Kitten",
            "Watch this adorable kitten playing with toys",
            "https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg?auto=compress&cs=tinysrgb&w=800&h=600",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        ),
        PetVideo(
            2,
            "Puppy Training",
            "Basic training tips for your new puppy",
            "https://images.pexels.com/photos/4587995/pexels-photo-4587995.jpeg?auto=compress&cs=tinysrgb&w=800&h=600",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
        ),
        PetVideo(
            3,
            "Cat Care Tips",
            "Essential tips for taking care of your cat",
            "https://images.pexels.com/photos/774731/pexels-photo-774731.jpeg?auto=compress&cs=tinysrgb&w=800&h=600",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
        ),
        PetVideo(
            4,
            "Dog Exercise",
            "Fun exercises to keep your dog healthy and happy",
            "https://images.pexels.com/photos/4588011/pexels-photo-4588011.jpeg?auto=compress&cs=tinysrgb&w=800&h=600",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"
        )
    )
    
    if (selectedVideo != null) {
        Box(modifier = Modifier.fillMaxSize()) {
            VideoPlayer(
                videoUri = selectedVideo!!.videoUrl,
                onBackClick = { selectedVideo = null }
            )
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Pet Videos") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(videos) { video ->
                    VideoCard(
                        video = video,
                        onVideoClick = { selectedVideo = video }
                    )
                }
            }
        }
    }
}

@Composable
fun VideoCard(
    video: PetVideo,
    onVideoClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        onClick = onVideoClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            SafeAsyncImage(
                model = video.thumbnailUrl,
                contentDescription = video.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            
            // Semi-transparent overlay
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.2f))
            )
            
            // Play icon
            Icon(
                imageVector = Icons.Default.PlayCircle,
                contentDescription = "Play video",
                tint = Color.White,
                modifier = Modifier
                    .size(72.dp)
                    .align(Alignment.Center)
            )
            
            // Video info overlay at bottom
            Surface(
                color = Color.Black.copy(alpha = 0.6f),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = video.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Text(
                        text = video.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
} 
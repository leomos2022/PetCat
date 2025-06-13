package com.example.firucat.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firucat.data.Repository
import com.example.firucat.ui.components.SafeAsyncImage
import com.example.firucat.ui.components.YouTubePlayer

@Composable
fun PetDetailScreen(navController: NavController, petId: Int) {
    val pet = Repository.getPetById(petId)
    val scrollState = rememberScrollState()
    var showVideo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        SafeAsyncImage(
            model = pet.imageUrl,
            contentDescription = "Pet Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = pet.name,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = pet.type,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = pet.description,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        pet.videoUrl?.let { videoUrl ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showVideo = !showVideo }
            ) {
                if (showVideo) {
                    YouTubePlayer(videoUrl = videoUrl)
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                    ) {
                        SafeAsyncImage(
                            model = pet.imageUrl,
                            contentDescription = "Video Thumbnail",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Icon(
                            imageVector = Icons.Default.PlayCircle,
                            contentDescription = "Play Video",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(72.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        Button(
            onClick = { /* Handle adoption inquiry */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Inquire About Adoption")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (pet.isAvailable) "Available for Adoption" else "Not Available for Adoption",
            style = MaterialTheme.typography.bodyMedium,
            color = if (pet.isAvailable)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.error
        )
    }
}

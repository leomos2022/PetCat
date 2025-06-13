package com.example.firucat.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var isLoading by remember { mutableStateOf(true) }
    
    LaunchedEffect(key1 = true) {
        delay(5000) // 5 seconds delay
        isLoading = false
        navController.navigate("products") {
            popUpTo("splash") { inclusive = true }
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "Petcat",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 56.sp,
                    color = Color(0xFF2E7D32) // Dark green color
                )
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "PARAISO ANIMAL",
                style = MaterialTheme.typography.titleLarge.copy(
                    letterSpacing = 6.sp,
                    color = Color(0xFF8BC34A), // Light green color
                    fontSize = 28.sp
                )
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Tu tienda de mascotas favorita",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color(0xFF666666),
                    fontSize = 20.sp
                )
            )
        }
    }
}

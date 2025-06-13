@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.firucat.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.firucat.ui.components.SafeAsyncImage

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val imageUrl: String,
    val description: String,
    val longDescription: String = ""
)

@Composable
fun ProductsScreen(navController: NavController) {
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    
    val products = listOf(
        Product(
            1,
            "Premium Cat Food",
            "$29.99",
            "https://m.media-amazon.com/images/I/7164MXbzrOL._AC_SL1500_.jpg",
            "High-quality premium cat food for all life stages",
            "Premium cat food made with real chicken as the first ingredient. Contains essential nutrients, vitamins, and minerals for optimal feline health. Suitable for indoor and outdoor cats of all ages. Promotes healthy digestion and maintains coat health."
        ),
        Product(
            2,
            "Interactive Dog Toy",
            "$14.99",
            "https://m.media-amazon.com/images/I/81vEhDb4rsL._AC_SL1500_.jpg",
            "Durable and engaging interactive toy for dogs",
            "Engaging interactive toy designed to keep your dog mentally stimulated and physically active. Made from durable materials, safe for aggressive chewers. Features treat dispensing mechanism and various textures for enhanced play experience."
        ),
        Product(
            3,
            "Cat Scratching Post",
            "$39.99",
            "https://m.media-amazon.com/images/I/81CDFCOT6OL._AC_SL1500_.jpg",
            "Multi-level cat scratching post with platforms",
            "Tall cat tree with multiple platforms and scratching posts. Made with premium sisal rope and soft plush covering. Includes cozy hideaway and perches at different heights. Perfect for multiple cat households."
        ),
        Product(
            4,
            "Cozy Dog Bed",
            "$49.99",
            "https://m.media-amazon.com/images/I/61hnzHbeM4L._AC_SL1500_.jpg",
            "Comfortable and washable bed for dogs",
            "Luxurious orthopedic dog bed with memory foam for maximum comfort. Features waterproof liner and machine-washable cover. Non-slip bottom keeps bed in place. Available in multiple sizes to suit all dog breeds."
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pet Products") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onClick = { selectedProduct = product }
                )
            }
        }
        
        // Product Detail Dialog
        selectedProduct?.let { product ->
            Dialog(
                onDismissRequest = { selectedProduct = null },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    usePlatformDefaultWidth = false
                )
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .padding(16.dp),
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        ) {
                            SafeAsyncImage(
                                model = product.imageUrl,
                                contentDescription = product.name,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = product.name,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = product.price,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = product.longDescription,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Button(
                            onClick = { selectedProduct = null },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Close")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.8f),
        onClick = onClick
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                SafeAsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
            
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
} 
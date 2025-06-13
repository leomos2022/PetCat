package com.example.firucat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.firucat.ui.screens.*
import com.example.firucat.ui.theme.FiruCatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiruCatTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    val items = listOf(
        Screen.Products,
        Screen.Pets,
        Screen.PetCare
    )
    
    val showBottomBar = remember(currentDestination) {
        currentDestination?.hierarchy?.any { destination ->
            items.any { it.route == destination.route }
        } ?: false
    }
    
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = when (screen) {
                                        Screen.Products -> Icons.Default.ShoppingCart
                                        Screen.Pets -> Icons.Default.Pets
                                        Screen.PetCare -> Icons.AutoMirrored.Filled.VolumeUp
                                    },
                                    contentDescription = screen.title
                                )
                            },
                            label = { Text(screen.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("splash") {
                SplashScreen(navController)
            }
            composable(Screen.Products.route) {
                ProductsScreen(navController)
            }
            composable(
                "product/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { backStackEntry ->
                ProductDetailScreen(
                    navController = navController,
                    productId = backStackEntry.arguments?.getInt("productId") ?: 0
                )
            }
            composable(Screen.Pets.route) {
                PetScreen(navController)
            }
            composable(
                "pet/{petId}",
                arguments = listOf(navArgument("petId") { type = NavType.IntType })
            ) { backStackEntry ->
                PetDetailScreen(
                    navController = navController,
                    petId = backStackEntry.arguments?.getInt("petId") ?: 0
                )
            }
            composable(Screen.PetCare.route) {
                PetCareAudioScreen(navController)
            }
        }
    }
}

sealed class Screen(val route: String, val title: String) {
    object Products : Screen("products", "Products")
    object Pets : Screen("pets", "Pets")
    object PetCare : Screen("petcare", "Pet Care")
}
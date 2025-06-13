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

/**
 * Actividad principal de la aplicación PetCat
 * 
 * Esta clase maneja la configuración inicial de la aplicación y establece
 * el tema y la navegación principal. Es el punto de entrada de la aplicación.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configura el contenido de la actividad usando Jetpack Compose
        setContent {
            // Aplica el tema personalizado de la aplicación
            FiruCatTheme {
                MainScreen()
            }
        }
    }
}

/**
 * Pantalla principal de la aplicación que contiene la navegación
 * 
 * Esta función composable maneja:
 * - La navegación entre pantallas principales
 * - La barra de navegación inferior
 * - El control de estado de navegación
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    // Controlador de navegación que maneja la navegación entre pantallas
    val navController = rememberNavController()
    
    // Estado actual de la navegación para determinar la pantalla activa
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    // Lista de pantallas principales disponibles en la aplicación
    val items = listOf(
        Screen.Products,  // Pantalla de productos
        Screen.Pets,      // Pantalla de mascotas
        Screen.PetCare    // Pantalla de cuidado de mascotas
    )
    
    // Determina si mostrar la barra de navegación inferior
    // Solo se muestra en las pantallas principales, no en pantallas de detalle
    val showBottomBar = remember(currentDestination) {
        currentDestination?.hierarchy?.any { destination ->
            items.any { it.route == destination.route }
        } ?: false
    }
    
    // Scaffold es el contenedor principal que proporciona la estructura básica
    Scaffold(
        // Barra de navegación inferior con iconos y navegación
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    items.forEach { screen ->
                        NavigationBarItem(
                            // Icono específico para cada pantalla
                            icon = {
                                Icon(
                                    imageVector = when (screen) {
                                        Screen.Products -> Icons.Default.ShoppingCart  // Carrito para productos
                                        Screen.Pets -> Icons.Default.Pets             // Pata para mascotas
                                        Screen.PetCare -> Icons.AutoMirrored.Filled.VolumeUp  // Altavoz para audio
                                    },
                                    contentDescription = screen.title
                                )
                            },
                            // Texto descriptivo de cada pantalla
                            label = { Text(screen.title) },
                            // Indica si esta pantalla está actualmente seleccionada
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            // Acción al hacer clic en el elemento de navegación
                            onClick = {
                                navController.navigate(screen.route) {
                                    // Configuración de navegación para evitar duplicados
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
        // NavHost define el grafo de navegación de la aplicación
        NavHost(
            navController = navController,
            startDestination = "splash",  // Pantalla de inicio (splash screen)
            modifier = Modifier.padding(paddingValues)
        ) {
            // Pantalla de splash (pantalla de carga inicial)
            composable("splash") {
                SplashScreen(navController)
            }
            
            // Pantalla principal de productos
            composable(Screen.Products.route) {
                ProductsScreen(navController)
            }
            
            // Pantalla de detalle de producto con parámetro dinámico
            composable(
                "product/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { backStackEntry ->
                ProductDetailScreen(
                    navController = navController,
                    productId = backStackEntry.arguments?.getInt("productId") ?: 0
                )
            }
            
            // Pantalla principal de mascotas
            composable(Screen.Pets.route) {
                PetScreen(navController)
            }
            
            // Pantalla de detalle de mascota con parámetro dinámico
            composable(
                "pet/{petId}",
                arguments = listOf(navArgument("petId") { type = NavType.IntType })
            ) { backStackEntry ->
                PetDetailScreen(
                    navController = navController,
                    petId = backStackEntry.arguments?.getInt("petId") ?: 0
                )
            }
            
            // Pantalla de cuidado de mascotas (audio)
            composable(Screen.PetCare.route) {
                PetCareAudioScreen(navController)
            }
        }
    }
}

/**
 * Clase sellada que define las pantallas principales de la aplicación
 * 
 * Cada objeto representa una pantalla con su ruta de navegación y título
 * Esta estructura permite un manejo seguro de la navegación
 */
sealed class Screen(val route: String, val title: String) {
    object Products : Screen("products", "Products")  // Pantalla de productos
    object Pets : Screen("pets", "Pets")              // Pantalla de mascotas
    object PetCare : Screen("petcare", "Pet Care")    // Pantalla de cuidado
}
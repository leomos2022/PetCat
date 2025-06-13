package com.example.firucat.model

/**
 * Modelo de datos para representar un producto en la tienda de mascotas
 * 
 * Esta clase de datos contiene toda la información necesaria para mostrar
 * un producto en el catálogo, incluyendo su identificación, detalles,
 * precio e imagen.
 * 
 * @param id Identificador único del producto
 * @param name Nombre del producto
 * @param description Descripción detallada del producto
 * @param price Precio del producto en formato decimal
 * @param imageUrl URL de la imagen del producto
 * @param category Categoría a la que pertenece el producto
 */
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String
)

/**
 * Enumeración que define las categorías de productos disponibles
 * 
 * Esta enumeración permite categorizar los productos de manera consistente
 * y facilita la organización y filtrado del catálogo.
 */
enum class ProductCategory {
    FOOD,        // Alimentos para mascotas
    TOYS,        // Juguetes para mascotas
    ACCESSORIES, // Accesorios (collares, correas, etc.)
    OTHER        // Otros productos no categorizados
} 
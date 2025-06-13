package com.example.firucat.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String
)

enum class ProductCategory {
    FOOD,
    TOYS,
    ACCESSORIES,
    OTHER
} 
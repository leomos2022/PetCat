package com.example.firucat.model

data class Pet(
    val id: Int,
    val name: String,
    val type: String,
    val description: String,
    val imageUrl: String,
    val videoUrl: String? = null,
    val isAvailable: Boolean = true
) 
package com.example.firucat.model

data class PetCareAudio(
    val id: Int,
    val title: String,
    val description: String,
    val audioUrl: String,
    val duration: Long, // Duration in milliseconds
    val petType: PetType,
    val author: String
) 
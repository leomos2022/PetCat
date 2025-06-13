package com.example.firucat.model

/**
 * Modelo de datos para representar una guía de audio sobre cuidado de mascotas
 * 
 * Esta clase de datos contiene toda la información necesaria para mostrar
 * y reproducir una guía de audio educativa sobre el cuidado de mascotas.
 * 
 * @param id Identificador único de la guía de audio
 * @param title Título de la guía de audio
 * @param description Descripción detallada del contenido de la guía
 * @param audioUrl URL del archivo de audio
 * @param duration Duración del audio en milisegundos
 * @param petType Tipo de mascota al que se refiere la guía
 * @param author Autor o creador de la guía de audio
 */
data class PetCareAudio(
    val id: Int,
    val title: String,
    val description: String,
    val audioUrl: String,
    val duration: Long, // Duración en milisegundos
    val petType: PetType,
    val author: String
) 
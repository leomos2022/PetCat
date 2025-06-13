package com.example.firucat.model

/**
 * Modelo de datos para representar una mascota en la galería
 * 
 * Esta clase de datos contiene toda la información necesaria para mostrar
 * una mascota en la galería, incluyendo su identificación, detalles,
 * imágenes, videos y estado de disponibilidad.
 * 
 * @param id Identificador único de la mascota
 * @param name Nombre de la mascota
 * @param type Tipo de mascota (perro, gato, etc.)
 * @param description Descripción detallada de la mascota
 * @param imageUrl URL de la imagen principal de la mascota
 * @param videoUrl URL opcional del video de la mascota (puede ser null)
 * @param isAvailable Indica si la mascota está disponible para adopción
 */
data class Pet(
    val id: Int,
    val name: String,
    val type: String,
    val description: String,
    val imageUrl: String,
    val videoUrl: String? = null,  // Video opcional de la mascota
    val isAvailable: Boolean = true  // Por defecto está disponible
) 
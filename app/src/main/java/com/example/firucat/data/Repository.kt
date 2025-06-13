package com.example.firucat.data

import com.example.firucat.model.Pet
import com.example.firucat.model.PetCareAudio
import com.example.firucat.model.PetType
import com.example.firucat.model.Product

/**
 * Repositorio de datos que proporciona acceso a los datos de la aplicación
 * 
 * Este objeto singleton actúa como fuente única de datos para toda la aplicación.
 * En una implementación real, estos datos vendrían de una base de datos o API.
 * 
 * Contiene:
 * - Lista de mascotas disponibles
 * - Catálogo de productos
 * - Guías de audio sobre cuidado de mascotas
 */
object Repository {
    /**
     * Lista de mascotas disponibles en la galería
     * 
     * Cada mascota incluye información completa como nombre, tipo,
     * descripción, imagen y video (opcional).
     */
    private val pets = listOf(
        Pet(
            id = 1,
            name = "Luna",
            type = "Cat",
            description = "A beautiful Siamese cat with blue eyes",
            imageUrl = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=800&h=600&fit=crop",
            videoUrl = "https://www.youtube.com/watch?v=J3NAYXXyBVI",
            isAvailable = true
        ),
        Pet(
            id = 2,
            name = "Max",
            type = "Dog",
            description = "A friendly Golden Retriever",
            imageUrl = "https://images.unsplash.com/photo-1552053831-71594a27632d?w=800&h=600&fit=crop",
            videoUrl = "https://www.youtube.com/watch?v=LZ9AKsUCcTI",
            isAvailable = false
        )
    )

    /**
     * Catálogo de productos disponibles en la tienda
     * 
     * Incluye productos de diferentes categorías como alimentos,
     * juguetes, accesorios y muebles para mascotas.
     */
    private val products = listOf(
        Product(
            id = 1,
            name = "Premium Cat Food",
            description = "High-quality cat food for all life stages",
            price = 29.99,
            imageUrl = "https://m.media-amazon.com/images/I/7164MXbzrOL._AC_SL1500_.jpg",
            category = "Food"
        ),
        Product(
            id = 2,
            name = "Interactive Dog Toy",
            description = "Durable toy that keeps dogs entertained",
            price = 14.99,
            imageUrl = "https://m.media-amazon.com/images/I/81vEhDb4rsL._AC_SL1500_.jpg",
            category = "Toys"
        ),
        Product(
            id = 3,
            name = "Cat Scratching Post",
            description = "Tall scratching post with platforms",
            price = 49.99,
            imageUrl = "https://m.media-amazon.com/images/I/81CDFCOT6OL._AC_SL1500_.jpg",
            category = "Furniture"
        ),
        Product(
            id = 4,
            name = "Dog Bed",
            description = "Comfortable bed for medium to large dogs",
            price = 39.99,
            imageUrl = "https://m.media-amazon.com/images/I/61hnzHbeM4L._AC_SL1500_.jpg",
            category = "Furniture"
        )
    )

    /**
     * Guías de audio sobre cuidado de mascotas
     * 
     * Contiene audio educativo sobre diferentes aspectos del cuidado
     * de mascotas, organizado por tipo de mascota y especialidad.
     */
    private val petCareAudioTracks = listOf(
        PetCareAudio(
            id = 1,
            title = "Basic Dog Training Tips",
            description = "Learn essential commands and positive reinforcement techniques for training your dog effectively.",
            audioUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3",
            duration = 720000, // 12 minutos
            petType = PetType.DOG,
            author = "Dr. Sarah Martinez, Professional Dog Trainer"
        ),
        PetCareAudio(
            id = 2,
            title = "Cat Behavior Understanding",
            description = "Understand your cat's body language and common behaviors to build a better relationship.",
            audioUrl = "https://download.samplelib.com/mp3/sample-15s.mp3",
            duration = 600000, // 10 minutos
            petType = PetType.CAT,
            author = "Dr. James Wilson, Feline Behavior Specialist"
        ),
        PetCareAudio(
            id = 3,
            title = "Puppy Care Essentials",
            description = "Everything you need to know about caring for a new puppy, including feeding, training, and health care.",
            audioUrl = "https://www2.cs.uic.edu/~i101/SoundFiles/BabyElephantWalk60.wav",
            duration = 900000, // 15 minutos
            petType = PetType.DOG,
            author = "Dr. Emily Carter, Veterinarian"
        ),
        PetCareAudio(
            id = 4,
            title = "Kitten First Month Guide",
            description = "A comprehensive guide to caring for your kitten during their crucial first month at home.",
            audioUrl = "https://download.samplelib.com/mp3/sample-9s.mp3",
            duration = 840000, // 14 minutos
            petType = PetType.CAT,
            author = "Dr. Lisa Thompson, Cat Care Specialist"
        ),
        PetCareAudio(
            id = 5,
            title = "Pet Nutrition Basics",
            description = "Learn about proper nutrition for both cats and dogs, including what foods to avoid.",
            audioUrl = "https://download.samplelib.com/mp3/sample-12s.mp3",
            duration = 780000, // 13 minutos
            petType = PetType.OTHER,
            author = "Dr. Michael Brown, Pet Nutritionist"
        ),
        PetCareAudio(
            id = 6,
            title = "Emergency Pet Care",
            description = "Important information about handling common pet emergencies and when to visit the vet.",
            audioUrl = "https://download.samplelib.com/mp3/sample-3s.mp3",
            duration = 660000, // 11 minutos
            petType = PetType.OTHER,
            author = "Dr. Rachel Green, Emergency Vet"
        )
    )

    // ==================== MÉTODOS DE ACCESO A DATOS ====================

    /**
     * Obtiene la lista completa de mascotas disponibles
     * @return Lista de todas las mascotas
     */
    fun getPets(): List<Pet> = pets

    /**
     * Busca una mascota específica por su ID
     * @param id Identificador único de la mascota
     * @return La mascota encontrada o lanza excepción si no existe
     */
    fun getPetById(id: Int): Pet = pets.first { it.id == id }

    /**
     * Obtiene la lista completa de productos disponibles
     * @return Lista de todos los productos
     */
    fun getProducts(): List<Product> = products

    /**
     * Busca un producto específico por su ID
     * @param id Identificador único del producto
     * @return El producto encontrado o lanza excepción si no existe
     */
    fun getProductById(id: Int): Product = products.first { it.id == id }

    /**
     * Obtiene la lista completa de guías de audio
     * @return Lista de todas las guías de audio
     */
    fun getPetCareAudioTracks(): List<PetCareAudio> = petCareAudioTracks

    /**
     * Busca una guía de audio específica por su ID
     * @param id Identificador único de la guía de audio
     * @return La guía de audio encontrada o null si no existe
     */
    fun getPetCareAudio(id: Long): PetCareAudio? = petCareAudioTracks.find { it.id.toLong() == id }
}

package com.example.firucat.data

import com.example.firucat.model.Pet
import com.example.firucat.model.PetCareAudio
import com.example.firucat.model.PetType
import com.example.firucat.model.Product

object Repository {
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

    private val petCareAudioTracks = listOf(
        PetCareAudio(
            id = 1,
            title = "Basic Dog Training Tips",
            description = "Learn essential commands and positive reinforcement techniques for training your dog effectively.",
            audioUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3",
            duration = 720000,
            petType = PetType.DOG,
            author = "Dr. Sarah Martinez, Professional Dog Trainer"
        ),
        PetCareAudio(
            id = 2,
            title = "Cat Behavior Understanding",
            description = "Understand your cat's body language and common behaviors to build a better relationship.",
            audioUrl = "https://download.samplelib.com/mp3/sample-15s.mp3",
            duration = 600000,
            petType = PetType.CAT,
            author = "Dr. James Wilson, Feline Behavior Specialist"
        ),
        PetCareAudio(
            id = 3,
            title = "Puppy Care Essentials",
            description = "Everything you need to know about caring for a new puppy, including feeding, training, and health care.",
            audioUrl = "https://www2.cs.uic.edu/~i101/SoundFiles/BabyElephantWalk60.wav",
            duration = 900000,
            petType = PetType.DOG,
            author = "Dr. Emily Carter, Veterinarian"
        ),
        PetCareAudio(
            id = 4,
            title = "Kitten First Month Guide",
            description = "A comprehensive guide to caring for your kitten during their crucial first month at home.",
            audioUrl = "https://download.samplelib.com/mp3/sample-9s.mp3",
            duration = 840000,
            petType = PetType.CAT,
            author = "Dr. Lisa Thompson, Cat Care Specialist"
        ),
        PetCareAudio(
            id = 5,
            title = "Pet Nutrition Basics",
            description = "Learn about proper nutrition for both cats and dogs, including what foods to avoid.",
            audioUrl = "https://download.samplelib.com/mp3/sample-12s.mp3",
            duration = 780000,
            petType = PetType.OTHER,
            author = "Dr. Michael Brown, Pet Nutritionist"
        ),
        PetCareAudio(
            id = 6,
            title = "Emergency Pet Care",
            description = "Important information about handling common pet emergencies and when to visit the vet.",
            audioUrl = "https://download.samplelib.com/mp3/sample-3s.mp3",
            duration = 660000,
            petType = PetType.OTHER,
            author = "Dr. Rachel Green, Emergency Vet"
        )
    )

    fun getPets(): List<Pet> = pets
    fun getPetById(id: Int): Pet = pets.first { it.id == id }
    fun getProducts(): List<Product> = products
    fun getProductById(id: Int): Product = products.first { it.id == id }
    fun getPetCareAudioTracks(): List<PetCareAudio> = petCareAudioTracks
    fun getPetCareAudio(id: Long): PetCareAudio? = petCareAudioTracks.find { it.id.toLong() == id }
}

# 🐱 PetCat - Tienda de Mascotas

Una aplicación móvil moderna desarrollada en Android para tiendas de mascotas, que permite a los usuarios explorar productos, visualizar mascotas disponibles y acceder a guías de cuidado mediante contenido multimedia.

## ✨ Características Principales

### 🛍️ Catálogo de Productos
- Lista de productos con imágenes de alta calidad
- Descripciones detalladas y precios
- Vista de detalles de productos
- Categorización por tipo de producto

### 🐾 Galería de Mascotas
- Imágenes y videos de mascotas disponibles
- Reproductor de video integrado con YouTube
- Información detallada de cada mascota
- Navegación fluida entre contenido multimedia

### 🎧 Guías de Audio
- Reproductor de audio nativo
- Guías de cuidado para diferentes tipos de mascotas
- Controles de reproducción personalizados
- Gestión automática del ciclo de vida

### 🎨 Interfaz Moderna
- Material Design 3
- Navegación con bottom navigation
- Temas dinámicos (claro/oscuro)
- Diseño responsivo para diferentes pantallas

## 🛠️ Tecnologías Utilizadas

### Frontend
- **Jetpack Compose 1.5.4** - UI declarativa moderna
- **Material Design 3** - Componentes de diseño
- **Navigation Component 2.7.5** - Navegación fluida

### Multimedia
- **Coil 2.5.0** - Carga eficiente de imágenes
- **ExoPlayer 1.2.0** - Reproducción de video
- **MediaPlayer** - Reproducción de audio
- **YouTube Player 12.1.0** - Integración con YouTube

### Arquitectura
- **MVVM** - Patrón de arquitectura
- **ViewModel** - Gestión de estado
- **StateFlow** - Reactividad
- **Coroutines** - Programación asíncrona

### Herramientas
- **Kotlin 1.9.0** - Lenguaje de programación
- **Android Studio Hedgehog** - IDE
- **Gradle 8.0** - Sistema de build

## 📱 Capturas de Pantalla

```
┌─────────────────────────────────────────────────────────────┐
│                    PetCat Application                      │
├─────────────────────────────────────────────────────────────┤
│  🛍️ Products  │  🐾 Pets  │  🎧 Pet Care                  │
│     ↓         │    ↓      │    ↓                          │
│ Product List  │ Pet List  │ Audio Player                  │
│     ↓         │    ↓      │    ↓                          │
│ Product       │ Video     │ Audio                         │
│ Details       │ Player    │ Guide                         │
└─────────────────────────────────────────────────────────────┘
```

## 🚀 Instalación

### Prerrequisitos
- Android Studio Hedgehog o superior
- Android SDK 34
- Kotlin 1.9.0
- Dispositivo Android con API level 24+

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/PetCat.git
   cd PetCat
   ```

2. **Abrir en Android Studio**
   - Abrir Android Studio
   - Seleccionar "Open an existing project"
   - Navegar a la carpeta del proyecto y seleccionarla

3. **Sincronizar el proyecto**
   - Esperar a que Gradle sincronice el proyecto
   - Resolver cualquier dependencia faltante

4. **Ejecutar la aplicación**
   - Conectar un dispositivo Android o usar un emulador
   - Presionar "Run" (▶️) en Android Studio

## 📁 Estructura del Proyecto

```
app/src/main/
├── java/com/example/firucat/
│   ├── MainActivity.kt
│   ├── FiruCatApp.kt
│   ├── ui/
│   │   ├── products/
│   │   │   ├── ProductsScreen.kt
│   │   │   ├── ProductsViewModel.kt
│   │   │   └── components/
│   │   ├── pets/
│   │   │   ├── PetsScreen.kt
│   │   │   ├── PetsViewModel.kt
│   │   │   └── components/
│   │   └── petcare/
│   │       ├── PetCareScreen.kt
│   │       ├── PetCareViewModel.kt
│   │       └── components/
│   ├── data/
│   │   ├── models/
│   │   └── repositories/
│   ├── utils/
│   │   ├── Constants.kt
│   │   └── Extensions.kt
│   └── di/
│       └── AppModule.kt
└── res/
    ├── drawable/
    ├── values/
    │   ├── colors.xml
    │   ├── strings.xml
    │   └── themes.xml
    └── layout/
```

## 🔧 Configuración

### Permisos Requeridos
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
```

### Dependencias Principales
```gradle
dependencies {
    // Navegación
    implementation "androidx.navigation:navigation-compose:2.7.5"
    
    // UI Moderna
    implementation "androidx.compose.material3:material3:1.1.2"
    implementation "androidx.compose.ui:ui:1.5.4"
    
    // Carga de Imágenes
    implementation "io.coil-kt:coil-compose:2.5.0"
    
    // Reproducción Multimedia
    implementation "androidx.media3:media3-exoplayer:1.2.0"
    implementation "androidx.media3:media3-ui:1.2.0"
    implementation "androidx.media3:media3-session:1.2.0"
    
    // YouTube Player
    implementation "com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0"
}
```

## 🎯 Funcionalidades Destacadas

### Optimizaciones de Rendimiento
- ✅ Lazy loading para listas grandes
- ✅ Cache inteligente para imágenes
- ✅ Pausado automático de multimedia
- ✅ Gestión eficiente de memoria

### Experiencia de Usuario
- ✅ Navegación intuitiva
- ✅ Feedback visual inmediato
- ✅ Manejo de errores con mensajes informativos
- ✅ Transiciones suaves

### Compatibilidad Multimedia
- ✅ Múltiples formatos de video (MP4, WebM, YouTube)
- ✅ Reproducción de audio nativo
- ✅ Validación de URLs antes de reproducción
- ✅ Fallbacks para contenido no disponible

## 📚 Documentación

### Análisis Técnico
- [Análisis Técnico Completo](Analisis_Tecnico_FiruCat.md) - Documento detallado de 50+ páginas
- [Resumen Ejecutivo](Resumen_Ejecutivo_FiruCat.md) - Resumen de 10+ páginas

### Referencias Académicas
- Google Developers - Android Jetpack Compose
- Jake Wharton - Coil Image Loading Library
- Google ExoPlayer Team - ExoPlayer Documentation
- Material Design Team - Material Design 3 Guidelines

## 🤝 Contribuir

1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 👨‍💻 Autor

**Leonardo Mosquera**
- GitHub: [@tu-usuario](https://github.com/tu-usuario)
- Email: tu-email@ejemplo.com

## 🙏 Agradecimientos

- Google Developers por las excelentes herramientas de Android
- La comunidad de Jetpack Compose por el soporte
- Los contribuidores de las librerías utilizadas

---

## 📊 Estadísticas del Proyecto

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white)
![Material Design](https://img.shields.io/badge/Material%20Design-757575?style=for-the-badge&logo=material-design&logoColor=white)

**Versión**: 1.0.0  
**Última actualización**: Diciembre 2024  
**Estado**: ✅ Completado 
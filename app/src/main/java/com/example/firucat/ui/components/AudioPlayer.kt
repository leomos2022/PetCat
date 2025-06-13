package com.example.firucat.ui.components

import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Componente reproductor de audio personalizado
 * 
 * Este componente proporciona una interfaz completa para reproducir archivos de audio,
 * incluyendo controles de reproducción, barra de progreso y manejo de errores.
 * 
 * Características:
 * - Reproducción/pausa de audio
 * - Barra de progreso interactiva
 * - Manejo automático del ciclo de vida
 * - Indicadores de carga y error
 * - Gestión eficiente de memoria
 * 
 * @param audioUrl URL del archivo de audio a reproducir
 */
@Composable
fun AudioPlayer(audioUrl: String) {
    // Estados del reproductor de audio
    var isPlaying by remember { mutableStateOf(false) }      // Indica si está reproduciéndose
    var isLoading by remember { mutableStateOf(false) }      // Indica si está cargando
    var error by remember { mutableStateOf<String?>(null) }  // Mensaje de error si existe
    var progress by remember { mutableStateOf(0f) }          // Progreso actual (0.0 a 1.0)
    var duration by remember { mutableStateOf(0) }           // Duración total en milisegundos
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) } // Instancia del MediaPlayer
    
    // Referencias necesarias para el manejo del ciclo de vida
    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()

    /**
     * Manejo del ciclo de vida de la aplicación
     * 
     * Este DisposableEffect se encarga de pausar y liberar recursos
     * cuando la aplicación pasa a segundo plano o se detiene.
     */
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    // Pausa la reproducción cuando la app pasa a segundo plano
                    mediaPlayer?.pause()
                    isPlaying = false
                }
                Lifecycle.Event.ON_STOP -> {
                    // Libera completamente los recursos cuando la app se detiene
                    mediaPlayer?.apply {
                        stop()
                        reset()
                        release()
                    }
                    mediaPlayer = null
                    isPlaying = false
                }
                else -> {}
            }
        }

        // Registra el observador del ciclo de vida
        lifecycleOwner.lifecycle.addObserver(observer)

        // Limpieza cuando el componente se destruye
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            mediaPlayer?.apply {
                stop()
                reset()
                release()
            }
            mediaPlayer = null
        }
    }

    /**
     * Actualización del progreso de reproducción
     * 
     * Este LaunchedEffect actualiza la barra de progreso cada 100ms
     * mientras el audio se está reproduciendo.
     */
    LaunchedEffect(isPlaying) {
        while (isPlaying && mediaPlayer != null) {
            progress = mediaPlayer?.currentPosition?.toFloat()?.div(duration) ?: 0f
            delay(100) // Actualiza cada 100ms
        }
    }

    // Interfaz de usuario del reproductor
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Fila principal con controles
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Botón de reproducción/pausa
            IconButton(
                onClick = {
                    if (isPlaying) {
                        // Pausa la reproducción actual
                        mediaPlayer?.pause()
                        isPlaying = false
                    } else {
                        if (mediaPlayer == null) {
                            // Crea una nueva instancia de MediaPlayer
                            isLoading = true
                            error = null
                            mediaPlayer = MediaPlayer().apply {
                                // Configura los listeners para manejar eventos
                                setOnPreparedListener {
                                    duration = it.duration
                                    it.start()
                                    isPlaying = true
                                    isLoading = false
                                }
                                setOnErrorListener { _, what, extra ->
                                    isLoading = false
                                    error = "Error playing audio: what=$what, extra=$extra"
                                    true
                                }
                                setOnCompletionListener {
                                    isPlaying = false
                                    progress = 0f
                                }
                                try {
                                    setDataSource(audioUrl)
                                    prepareAsync()
                                } catch (e: Exception) {
                                    error = "Error loading audio: ${e.message}"
                                    isLoading = false
                                }
                            }
                        } else {
                            // Reanuda la reproducción existente
                            mediaPlayer?.start()
                            isPlaying = true
                        }
                    }
                },
                enabled = !isLoading
            ) {
                if (isLoading) {
                    // Indicador de carga mientras se prepara el audio
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    // Icono de reproducción o pausa
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Barra de progreso interactiva
            Box(modifier = Modifier.weight(1f)) {
                Slider(
                    value = progress,
                    onValueChange = { newProgress ->
                        scope.launch {
                            progress = newProgress
                            // Busca a la posición específica en el audio
                            mediaPlayer?.seekTo((duration * newProgress).toInt())
                        }
                    },
                    enabled = !isLoading && error == null && mediaPlayer != null
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Icono de volumen
            Icon(
                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                contentDescription = "Volume",
                modifier = Modifier.size(24.dp)
            )
        }

        // Mostrar mensaje de error si existe
        error?.let { errorMessage ->
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = "Error",
                    tint = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

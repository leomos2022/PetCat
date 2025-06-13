package com.example.firucat.ui.components

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun YouTubePlayer(videoUrl: String) {
    val videoId = extractYouTubeVideoId(videoUrl)
    
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f/9f),
        factory = { context ->
            WebView(context).apply {
                settings.apply {
                    javaScriptEnabled = true
                    mediaPlaybackRequiresUserGesture = false
                }
                webChromeClient = WebChromeClient()
                webViewClient = WebViewClient()
                
                val embedHtml = """
                    <!DOCTYPE html>
                    <html>
                        <body style="margin:0;">
                            <iframe 
                                width="100%" 
                                height="100%" 
                                src="https://www.youtube.com/embed/$videoId"
                                frameborder="0" 
                                allowfullscreen>
                            </iframe>
                        </body>
                    </html>
                """.trimIndent()
                
                loadData(embedHtml, "text/html", "utf-8")
            }
        }
    )
}

private fun extractYouTubeVideoId(url: String): String {
    val pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*"
    val regex = Regex(pattern)
    return regex.find(url)?.value ?: ""
} 
package com.botsheloramela.basicweatherapp.utils
import androidx.compose.ui.unit.dp
import com.botsheloramela.basicweatherapp.BuildConfig

/**
 * Object that contains constants used throughout the app
 */
object Constants {
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val API_KEY = BuildConfig.API_KEY

    val SCREEN_PADDING = 16.dp
}
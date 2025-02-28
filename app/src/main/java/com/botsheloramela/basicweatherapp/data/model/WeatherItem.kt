package com.botsheloramela.basicweatherapp.data.model

/**
 * Data class representing the weather item, which is a forecast for a specific date and time
 */
data class WeatherItem (
    val main: MainData,
    val weather: List<WeatherCondition>,
    val wind: Wind,
    val dt_txt: String // Date and time in text format
)
package com.botsheloramela.basicweatherapp.data.model

/**
 * Data class representing the main data of the weather, which includes the temperature, pressure, and humidity
 */
data class MainData(
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double
)
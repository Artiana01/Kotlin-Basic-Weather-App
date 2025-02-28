package com.botsheloramela.basicweatherapp.data.repository

import com.botsheloramela.basicweatherapp.data.remote.WeatherApi
import com.botsheloramela.basicweatherapp.data.model.CurrentWeather
import com.botsheloramela.basicweatherapp.data.model.WeatherForecast
import javax.inject.Inject

/**
 * Repository for weather data.
 */
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherForecast(latitude: Double, longitude: Double): WeatherForecast {
        return weatherApi.getWeatherForecast(latitude, longitude)
    }

    override suspend fun getCurrentWeather(latitude: Double, longitude: Double): CurrentWeather {
        return weatherApi.getCurrentWeather(latitude, longitude)
    }
}
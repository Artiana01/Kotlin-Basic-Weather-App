package com.botsheloramela.basicweatherapp.domain.usecase

import com.botsheloramela.basicweatherapp.data.repository.WeatherRepository
import com.botsheloramela.basicweatherapp.data.model.CurrentWeather
import javax.inject.Inject

/**
 * Represents the use case to get the current weather.
 *
 * @param weatherRepository The repository for weather data.
 */
class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(latitude: Double, longitude: Double): CurrentWeather {
        return weatherRepository.getCurrentWeather(latitude, longitude)
    }
}
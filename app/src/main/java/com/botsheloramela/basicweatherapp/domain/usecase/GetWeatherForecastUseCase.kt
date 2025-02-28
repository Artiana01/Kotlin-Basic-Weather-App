package com.botsheloramela.basicweatherapp.domain.usecase

import com.botsheloramela.basicweatherapp.data.repository.WeatherRepository
import com.botsheloramela.basicweatherapp.data.model.WeatherForecast
import javax.inject.Inject

/**
 * Represents the use case to get the weather forecast.
 *
 * @param weatherRepository The repository for weather data.
 */
class GetWeatherForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(latitude: Double, longitude: Double): WeatherForecast {
        return weatherRepository.getWeatherForecast(latitude, longitude)
    }
}
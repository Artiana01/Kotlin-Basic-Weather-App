package com.botsheloramela.basicweatherapp.data.repository

import com.botsheloramela.basicweatherapp.data.model.CurrentWeather
import com.botsheloramela.basicweatherapp.data.model.WeatherForecast
import com.botsheloramela.basicweatherapp.data.remote.WeatherApi
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
@ExperimentalCoroutinesApi
class WeatherRepositoryImplTest {
    private lateinit var weatherRepository: WeatherRepositoryImpl
    private val weatherApi: WeatherApi = mock()

    @Before
    fun setUp() {
        weatherRepository = WeatherRepositoryImpl(weatherApi)
    }

    @Test
    fun `getWeatherForecast returns forecast on valid coordinates`() = runBlockingTest {
        val mockForecast = mock<WeatherForecast>()
        val latitude = 34.0522
        val longitude = -118.2437

        `when`(weatherApi.getWeatherForecast(latitude, longitude)).thenReturn(mockForecast)

        val result = weatherRepository.getWeatherForecast(latitude, longitude)

        verify(weatherApi).getWeatherForecast(latitude, longitude)
        assertEquals(mockForecast, result)
    }

    @Test
    fun `getCurrentWeather returns current weather on valid coordinates` () = runBlockingTest {
        val mockCurrentWeather = mock<CurrentWeather>()
        val latitude = 34.0522
        val longitude = -118.2437

        `when`(weatherApi.getCurrentWeather(latitude, longitude)).thenReturn(mockCurrentWeather)

        val result = weatherRepository.getCurrentWeather(latitude, longitude)

        verify(weatherApi).getCurrentWeather(latitude, longitude)
        assertEquals(mockCurrentWeather, result)
    }
}
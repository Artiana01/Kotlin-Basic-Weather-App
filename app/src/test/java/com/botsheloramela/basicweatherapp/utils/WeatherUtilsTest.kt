package com.botsheloramela.basicweatherapp.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test
import com.botsheloramela.basicweatherapp.R

class WeatherUtilsTest {
    @Test
    fun `getWeatherIcon - thunderstorm id - returns thunderstorm icon`() {
        val thunderstormId = 201
        val expectedIcon = R.drawable.thunderstorm_light
        val result = WeatherUtils.getWeatherIcon(thunderstormId)
        assertEquals(expectedIcon, result)
    }

    @Test
    fun `getWeatherIcon - rain id - returns rain icon`() {
        val rainId = 502
        val expectedIcon = R.drawable.rain_light
        val result = WeatherUtils.getWeatherIcon(rainId)
        assertEquals(expectedIcon, result)
    }

    @Test
    fun `getWeatherImage - thunderstorm id - returns thunderstorm image`() {
        val thunderstormId = 201
        val expectedImage = R.drawable.thunder
        val result = WeatherUtils.getWeatherImage(thunderstormId)
        assertEquals(expectedImage, result)
    }

    @Test
    fun `getWeatherImage - rain id - returns rain image`() {
        val rainId = 502
        val expectedImage = R.drawable.rain
        val result = WeatherUtils.getWeatherImage(rainId)
        assertEquals(expectedImage, result)
    }
}
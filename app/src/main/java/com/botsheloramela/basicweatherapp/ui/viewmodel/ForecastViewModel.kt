package com.botsheloramela.basicweatherapp.ui.viewmodel

import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.botsheloramela.basicweatherapp.data.model.WeatherForecast
import com.botsheloramela.basicweatherapp.data.model.WeatherItem
import com.botsheloramela.basicweatherapp.domain.usecase.GetDeviceLocationUseCase
import com.botsheloramela.basicweatherapp.domain.usecase.GetWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val getDeviceLocationUseCase: GetDeviceLocationUseCase,
): ViewModel() {
    val locationState = mutableStateOf<Location?>(null)
    val weatherForecastState = mutableStateOf<WeatherForecast?>(null)

    /**
     * Fetch the device location.
     */
    fun getDeviceLocation() {
        viewModelScope.launch {
            val location = getDeviceLocationUseCase()
            locationState.value = location
        }
    }

    /**
     * Fetch the weather forecast based on the current location.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun getWeatherForecast() {
        viewModelScope.launch {
            val location = locationState.value
            if (location != null) {
                val weatherForecast = getWeatherForecastUseCase(
                    latitude = location.latitude,
                    longitude = location.longitude
                )

                val filteredForecasts = filterForecasts(weatherForecast)

                weatherForecastState.value = weatherForecast.copy(list = filteredForecasts)
            }
        }
    }

    /**
     * Filter the weather forecast to only include the forecasts for 6 AM each day.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun filterForecasts(weatherForecast: WeatherForecast): List<WeatherItem> {
        return weatherForecast.list.filter { item ->
            // Extract the time part of the dt_txt field
            val forecastTime = item.dt_txt.split(" ")[1] // Get the time part (HH:mm:ss)
            forecastTime == "06:00:00"
        }

    }
}
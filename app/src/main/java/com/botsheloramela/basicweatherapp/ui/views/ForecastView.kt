package com.botsheloramela.basicweatherapp.ui.views

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.botsheloramela.basicweatherapp.ui.components.FiveDayWeatherForecastCard
import com.botsheloramela.basicweatherapp.ui.viewmodel.ForecastViewModel
import com.botsheloramela.basicweatherapp.utils.Constants.SCREEN_PADDING
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ForecastView(
    viewModel: ForecastViewModel
) {
    val location by viewModel.locationState
    val weatherForecast by viewModel.weatherForecastState
    val permissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)

    // Fetch weather when screen is displayed
    LaunchedEffect(Unit) {
        if (permissionState.status.isGranted) {
            viewModel.getDeviceLocation()
        } else {
            permissionState.launchPermissionRequest()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SCREEN_PADDING),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {

        if (location != null) {
            LaunchedEffect(location) {
                viewModel.getWeatherForecast()
            }

            if (weatherForecast != null) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "5-Day Forecast",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    FiveDayWeatherForecastCard(weatherForecast = weatherForecast!!)
                }
            } else {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Fetching weather data...", color = MaterialTheme.colorScheme.onPrimary)
            }

        } else if (location == null) {
            Text(text = "Error: Location not found", color = MaterialTheme.colorScheme.error)
        } else {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "Fetching location...", color = MaterialTheme.colorScheme.onPrimary)
        }

    }
}
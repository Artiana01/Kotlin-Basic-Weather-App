package com.botsheloramela.basicweatherapp.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.botsheloramela.basicweatherapp.ui.theme.BasicWeatherAppTheme
import com.botsheloramela.basicweatherapp.utils.DateTimeUtils
import com.botsheloramela.basicweatherapp.utils.WeatherUtils
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyForecastCard(
    weatherIcons: List<Int>,
    hourlyTemps: List<Int>,
    hourlyTimes: List<String>
) {
    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            val columns = 5
            val spacing = 12.dp

            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                horizontalArrangement = Arrangement.spacedBy(spacing),
            ) {
                items(weatherIcons.size) { index ->
                    HourlyForecastItem(
                        weatherIcon = weatherIcons[index],
                        hourlyTemp = hourlyTemps[index],
                        hourlyTime = hourlyTimes[index]
                    )
                }
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyForecastItem(
    weatherIcon: Int,
    hourlyTemp: Int,
    hourlyTime: String
) {
    val icon = WeatherUtils.getWeatherIcon(weatherIcon)
    val timeStamp = DateTimeUtils.parseDtTxtToHour(hourlyTime)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = timeStamp.uppercase(Locale.ROOT),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.tertiary
        )
        Image(
            painter = painterResource(id = icon),
            contentDescription = "Weather icon",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$hourlyTemp°C",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HourlyForecastCardPreview() {
    BasicWeatherAppTheme {
        HourlyForecastCard(
            weatherIcons = listOf(
                200, 300, 700, 600, 500
            ),
            hourlyTemps = listOf(20, 20, 20, 20, 20),
            hourlyTimes = listOf("NOW", "13:00", "14:00", "15:00", "16:00")
        )
    }
}
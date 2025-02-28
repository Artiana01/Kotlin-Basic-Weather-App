package com.botsheloramela.basicweatherapp.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.botsheloramela.basicweatherapp.data.model.WeatherForecast
import com.botsheloramela.basicweatherapp.data.model.WeatherItem
import com.botsheloramela.basicweatherapp.utils.DateTimeUtils
import com.botsheloramela.basicweatherapp.utils.WeatherUtils

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FiveDayWeatherForecastCard(
    weatherForecast: WeatherForecast
) {
    val weatherForecastItems: List<WeatherItem> = weatherForecast.list

    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier.fillMaxWidth().heightIn(max = 350.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Column {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(5) { index ->
                        WeatherForecastItem(weatherItem = weatherForecastItems[index])
                        if (index in 1 until weatherForecastItems.size) {
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecastItem(
    weatherItem: WeatherItem
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        val dayMonth: String = DateTimeUtils.parseDtTxtToDayMonth(weatherItem.dt_txt)
        val weatherIconId: Int = weatherItem.weather[0].id

        val weatherIcon: Int = WeatherUtils.getWeatherIcon(weatherIconId)

        Text(
            text = dayMonth,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.tertiary,
        )

        Image(
            painter = painterResource(id = weatherIcon),
            contentDescription = "Weather Icon",
            modifier = Modifier.size(44.dp)
        )

        Text(
            text = "${weatherItem.main.temp_min.toInt()}°C",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = "${weatherItem.main.temp_max.toInt()}°C",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = weatherItem.weather[0].main.uppercase(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.tertiary,
            maxLines = 2,
            textAlign = TextAlign.End,
        )

    }
}
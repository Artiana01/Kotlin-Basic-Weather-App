package com.botsheloramela.basicweatherapp.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.botsheloramela.basicweatherapp.utils.DateTimeUtils
import com.botsheloramela.basicweatherapp.utils.WeatherUtils
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainWeatherCard(
    currentTemp: Int,
    feelsLikeTemp: Int,
    weatherType: String,
    location: String,
) {
    val currentDate = DateTimeUtils.getCurrentDate()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp / 1.2f
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenWidth),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(
                alpha = 0.5f
            ),
        ),
    ) {
        Box {
            val weatherImage = WeatherUtils.getWeatherImage(800)
            Image(
                painter = painterResource(id = weatherImage),
                contentDescription = "Weather Image",
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box (modifier = Modifier
                .background(Color.Black.copy(alpha = 0.6f))
                .fillMaxSize())
            // color tint
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = currentDate,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(48.dp))
                Box(
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "$currentTemp°C",
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.displayLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Feels like $feelsLikeTemp°C",
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = weatherType.uppercase(Locale.getDefault()),
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.weight(1.0f))
                        Text(
                            text = location,
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }


            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MainWeatherCardPreview() {
    MainWeatherCard(
        currentTemp = 22,
        feelsLikeTemp = 20,
        weatherType = "Clear sky",
        location = "Johannesburg"
    )
}

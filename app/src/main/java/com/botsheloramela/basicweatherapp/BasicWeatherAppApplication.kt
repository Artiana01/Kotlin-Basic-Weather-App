package com.botsheloramela.basicweatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The [Application] class for the Basic Weather App.
 * This class is annotated with [HiltAndroidApp] to enable Hilt for the app.
 */
@HiltAndroidApp
class BasicWeatherAppApplication: Application()
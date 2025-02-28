package com.botsheloramela.basicweatherapp.data.repository

import android.location.Location

/**
 * Repository for location data.
 */
interface LocationRepository  {
    suspend fun getDeviceLocation(): Location?
}
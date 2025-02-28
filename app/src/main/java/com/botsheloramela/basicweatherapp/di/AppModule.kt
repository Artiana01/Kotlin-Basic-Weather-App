package com.botsheloramela.basicweatherapp.di

import com.botsheloramela.basicweatherapp.data.remote.WeatherApi
import com.botsheloramela.basicweatherapp.data.repository.WeatherRepository
import com.botsheloramela.basicweatherapp.data.repository.WeatherRepositoryImpl
import com.botsheloramela.basicweatherapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module that provides dependencies for the app.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository = WeatherRepositoryImpl(weatherApi)
}
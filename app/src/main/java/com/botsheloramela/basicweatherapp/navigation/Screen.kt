package com.botsheloramela.basicweatherapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    /**
     * This companion object provides a way to create a Screen object from a route string.
     * It is used to navigate to a screen based on a route string.
     * The route string is used to identify the screen to navigate to.
     */
    companion object {
        fun fromRoute(route: String): Screen? {
            return Screen::class.sealedSubclasses.firstOrNull {
                route.contains(it.qualifiedName.toString())
            }?.objectInstance
        }
    }

    @Serializable
    data object Home : Screen()

    @Serializable
    data object Forecast : Screen()
}
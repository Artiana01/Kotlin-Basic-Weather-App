package com.botsheloramela.basicweatherapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.botsheloramela.basicweatherapp.R
import com.botsheloramela.basicweatherapp.ui.components.BottomNavItem
import com.botsheloramela.basicweatherapp.ui.components.BottomNavigationBar
import com.botsheloramela.basicweatherapp.ui.viewmodel.ForecastViewModel
import com.botsheloramela.basicweatherapp.ui.viewmodel.HomeViewModel
import com.botsheloramela.basicweatherapp.ui.views.ForecastView
import com.botsheloramela.basicweatherapp.ui.views.HomeView

/**
 * NavGraph composable function that defines the navigation graph for the app.
 *
 * @param navController the navigation controller
 * @param startDestination the start destination of the navigation graph
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Screen = Screen.Home
) {
    val bottomNavItems = remember {
        listOf(
            BottomNavItem(Screen.Home, R.drawable.home, "Home"),
            BottomNavItem(Screen.Forecast, R.drawable.compass, "Forecasts"),
        )
    }

    val backStackEntry= navController.currentBackStackEntryAsState().value
    var currentRoute = remember(backStackEntry) {
        Screen.fromRoute(backStackEntry?.destination?.route ?: "")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavItems,
                onItemClick = { index ->
                    val screen = bottomNavItems[index].route
                    navigateToTab(navController, screen)
                },
                selectedItem = currentRoute
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.fillMaxSize(),
        ) {
            composable<Screen.Home> {
                val homeViewModel: HomeViewModel = hiltViewModel()
                HomeView(viewModel = homeViewModel)
            }

            composable<Screen.Forecast> {
                val forecastViewModel: ForecastViewModel = hiltViewModel()
                ForecastView(viewModel = forecastViewModel)
            }
        }
    }
}

/**
 * Navigate to the specified tab.
 *
 * @param navController the navigation controller
 * @param screen the screen to navigate to
 */
private fun navigateToTab(navController: NavController, screen: Screen) {
    navController.navigate(screen) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
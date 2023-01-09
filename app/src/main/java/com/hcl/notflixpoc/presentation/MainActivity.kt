package com.hcl.notflixpoc.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hcl.notflixpoc.presentation.components.BottomNavBar
import com.hcl.notflixpoc.presentation.features.NavigationItem
import com.hcl.notflixpoc.presentation.features.detail.DetailScreenContainer
import com.hcl.notflixpoc.presentation.features.favorites.FavoritesScreen
import com.hcl.notflixpoc.presentation.features.home.HomeScreenContainer
import com.hcl.notflixpoc.presentation.features.notflixPoCTabRowScreens
import com.hcl.notflixpoc.presentation.features.settings.SettingsScreen
import com.hcl.notflixpoc.presentation.theme.NotflixPoCTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotflixPoCApp()
        }
    }

}

@Composable
fun NotflixPoCApp() {
    NotflixPoCTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            notflixPoCTabRowScreens.find { it.route == currentDestination?.route }
                ?: NavigationItem.Home

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreenContainer()
        }

        Scaffold(
            bottomBar = {
                BottomNavBar(
                    allScreens = notflixPoCTabRowScreens,
                    onTabSelected = { screen -> navController.navigateSingleTopTo(screen.route) },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            NotflixPoCNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

private fun NavHostController.navigateToMovieDetail(movieId: Int) {
    this.navigateSingleTopTo("${NavigationItem.Details.route}/$movieId")
}


@Composable
fun NotflixPoCNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route,
        modifier = modifier
    ) {
        composable(route = NavigationItem.Home.route) {
            HomeScreenContainer(onClickMovie = { movieId ->
                navController.navigateToMovieDetail(movieId = movieId)

            })
        }
        composable(route = NavigationItem.Favorites.route) {
            FavoritesScreen()
        }
        composable(route = NavigationItem.Settings.route) {
            SettingsScreen()
        }

        composable(
            route = NavigationItem.Details.routeWithArgs,
            arguments = NavigationItem.Details.arguments
        ) { navBackStackEntry ->
            // Retrieve the passed argument
            val movieId = navBackStackEntry.arguments?.getInt(NavigationItem.Details.movieIdArg)
            DetailScreenContainer(movieId = movieId)
        }

    }

}


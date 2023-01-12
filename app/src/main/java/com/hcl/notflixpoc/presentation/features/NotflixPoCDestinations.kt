package com.hcl.notflixpoc.presentation.features

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.hcl.notflixpoc.R

sealed class NavigationItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int?
) {

    object Home : NavigationItem("home", R.string.title_home, R.drawable.ic_home)
    object Favorites :
        NavigationItem("favorites", R.string.title_favorites, R.drawable.ic_favourite)

    object Settings : NavigationItem("settings", R.string.title_settings, R.drawable.ic_settings)

    object Details : NavigationItem("details", R.string.title_details, null) {
        const val movieIdArg = "movieId"
        val routeWithArgs = "$route/{$movieIdArg}"
        val arguments = listOf(
            navArgument(movieIdArg) { type = NavType.IntType }
        )
    }
}

val notflixPoCTabRowScreens =
    listOf(NavigationItem.Home, NavigationItem.Favorites, NavigationItem.Settings)

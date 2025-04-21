package com.student.mentalpotion.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppDestinations(
    val route: String,
    val label: String? = null,
    val icon: ImageVector? = null
) {
    object Home : AppDestinations("home", "Home", Icons.Default.Home)
    object Activities : AppDestinations("activities", "Activities", Icons.Default.List)
    object Profile : AppDestinations("profile", "Profile", Icons.Default.Person)

    // Auth
    object Landing : AppDestinations("landing")
    object Login : AppDestinations("login")
    object Register : AppDestinations("register")

    // List of screens for the nav bar
    companion object {
        val bottomBarItems = listOf(Home, Activities, Profile)
    }
}
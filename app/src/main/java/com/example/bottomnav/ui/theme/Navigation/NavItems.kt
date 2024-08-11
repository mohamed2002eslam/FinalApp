package com.example.bottomnav.ui.theme.Navigation

import android.health.connect.datatypes.ExerciseRoute
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val listOfNavItems: List<NavItem> = listOf(
    NavItem(
        label = "Home",
        icon = Icons.Default.Home,
        route = Screens.HomeScreen.name
    ),
    NavItem(
        label = "Profile",
        icon = Icons.Default.Person,
        route = Screens.ProfileScreen.name
    ),
    NavItem(
        label = "Settings",
        icon = Icons.Default.Settings,
        route = Screens.SettingsScreen.name

    )
)
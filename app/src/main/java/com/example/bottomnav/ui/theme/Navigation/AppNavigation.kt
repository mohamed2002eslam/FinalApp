package com.example.bottomnav.ui.theme.Navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnav.Screens.SettingsScreen.SettingsScreen
import com.example.bottomnav.Screens.SettingsScreen.SettingsViewModel
import com.example.bottomnav.Screens.homeScreen.HomeScreen
import com.example.bottomnav.Screens.profileScreen.ProfileScreen

@Composable
fun AppNavigation(){
    val navController: NavController = rememberNavController()
    val todoViewModel: SettingsViewModel = viewModel()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry : NavBackStackEntry? by navController.currentBackStackEntryAsState()
                val currentDestination: NavDestination? = navBackStackEntry?.destination

                listOfNavItems.forEach { navItem: NavItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any{it.route==navItem.route}==true,
                        onClick = {
                            navController.navigate(navItem.route){
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState=true
                                }
                                launchSingleTop=true
                                restoreState=true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }

        }
    ) { paddingValues : PaddingValues ->
        NavHost(
            navController = navController as NavHostController,
            startDestination = Screens.HomeScreen.name,
            modifier = Modifier
                .padding(paddingValues)

        ){
            composable(route = Screens.HomeScreen.name){
                HomeScreen()
            }
            composable(route = Screens.ProfileScreen.name){
                ProfileScreen()
            }
            composable(route = Screens.SettingsScreen.name){
                SettingsScreen(todoViewModel)
            }

        }

    }
}
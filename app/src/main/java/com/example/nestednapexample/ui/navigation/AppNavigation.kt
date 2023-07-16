package com.example.nestednapexample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.nestednapexample.ui.screens.auth.RestorePasswordScreen
import com.example.nestednapexample.ui.screens.auth.LoginScreen
import com.example.nestednapexample.ui.screens.userPrivate.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "auth") {
        navigation(
            startDestination = "login_screen",
            route = "auth"
        ) {
            composable("login_screen") {
                LoginScreen(navController)
            }
            composable("restore_password_screen") {
                RestorePasswordScreen(navController)
            }
        }

        navigation(
            startDestination = "home_screen",
            route = "home"
        ) {
            composable("home_screen"){
                HomeScreen(navController)
            }
        }


    }

}
package com.example.cstp2205_final.view.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cstp2205_final.auth.Auth
import com.example.cstp2205_final.view.screens.*

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun Navigation(auth: Auth) {
    val navController = rememberNavController()
    val startDestination by remember {
        mutableStateOf(
            when (auth.currentUser) {
                null -> Screen.SplashScreen.route
                else -> Screen.SplashScreen.route
            }
        )
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController, auth)
        }

        composable(route = Screen.MainScreen.route) {
            MainScreen(navController)
        }

        composable(route = Screen.SignInScreen.route) {
            SignInScreen(navController = navController, auth)
        }

        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController, auth)
        }

        composable(route = Screen.ShareTreesScreen.route) {
            ShareTreesScreen()
        }
    }
}
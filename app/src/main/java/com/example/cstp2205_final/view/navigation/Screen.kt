package com.example.cstp2205_final.view.navigation

import androidx.annotation.StringRes
import com.example.cstp2205_final.R

sealed class Screen(val route: String, @StringRes val resourceID: Int) {
    object SplashScreen : Screen("splashScreen", R.string.splash_screen)
    object MainScreen : Screen("mainScreen", R.string.main_screen)
    object SignInScreen : Screen("signInScreen", R.string.sign_in_screen)
    object SignUpScreen : Screen("signUpScreen", R.string.sign_up_screen)
    object ShareTreesScreen : Screen("shareTreesScreen", R.string.share_trees_screen)
}

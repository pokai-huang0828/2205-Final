package com.example.cstp2205_final.view.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cstp2205_final.R
import com.example.cstp2205_final.auth.Auth
import com.example.cstp2205_final.theme.MainGreen
import com.example.cstp2205_final.view.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, auth: Auth) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )

        delay(2000L)
        navController.navigate(Screen.SignInScreen.route)
        when (auth.currentUser) {
            null -> navController.navigate(Screen.SignInScreen.route)
            else -> navController.navigate(Screen.MainScreen.route)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(900.dp)
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.tree_icon),
            contentDescription = "Splash Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(600.dp)
                .scale(scale.value)
        )
        Text(
            text = "MY TREE",
            style = MaterialTheme.typography.h1,
            fontSize = 30.sp,
            color = MainGreen,
            fontWeight = FontWeight.Bold
        )
    }

}
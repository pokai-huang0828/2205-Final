package com.example.cstp2205_final.view.composables

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cstp2205_final.theme.MainGreen
import com.example.cstp2205_final.view.navigation.Screen

@ExperimentalComposeUiApi
@Composable
fun TopAddBT(navController: NavController) {

    // Button animation
    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 0.85f else 1f)

    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        modifier = Modifier
            .padding(top = 20.dp)
            .background(Color.White, CircleShape)
            .border(width = 2.dp, color = MainGreen, shape = CircleShape)
            .scale(scale.value)
            .clip(CircleShape)
            .size(30.dp)
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        selected.value = true
                        navController.navigate(Screen.ShareTreesScreen.route)
                    }
                    MotionEvent.ACTION_UP -> {
                        selected.value = false
                    }
                }
                true
            },
        contentPadding = PaddingValues(1.dp),
        content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "",
                tint = MainGreen,
                modifier = Modifier.size(35.dp)
            )
        }
    )
}
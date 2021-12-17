package com.example.cstp2205_final.view.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cstp2205_final.theme.GillSans
import com.example.cstp2205_final.theme.SecondGreen

@Composable
fun orSignInWith() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth(0.7f)
    ) {
        Divider(
            color = SecondGreen,
            modifier = Modifier.width(80.dp)
        )

        Text(
            text = "  or sign in with  ",
            fontFamily = GillSans,
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
        )

        Divider(
            color = SecondGreen,
            modifier = Modifier.width(80.dp)
        )
    }
}
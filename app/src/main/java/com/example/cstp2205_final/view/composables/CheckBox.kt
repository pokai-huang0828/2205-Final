package com.example.cstp2205_final.view.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cstp2205_final.theme.GillSans
import com.example.cstp2205_final.theme.SecondGreen

@Composable
fun LoginCheckBox() {

    var visible by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .size(20.dp)
                .background(White)
                .border(width = 1.dp, color = SecondGreen, shape = RoundedCornerShape(5.dp))
                .clickable { visible = !visible }
        ) {
            this@Row.AnimatedVisibility(visible){
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                    tint = SecondGreen,
                )
            }
        }
        Text(
            text = "  remember me",
            fontFamily = GillSans,
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = SecondGreen
        )
    }
}
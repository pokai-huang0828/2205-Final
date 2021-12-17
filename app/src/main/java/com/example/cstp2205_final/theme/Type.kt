package com.example.cstp2205_final.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cstp2205_final.R

val GillSans = FontFamily(
    Font(R.font.gill_sans_medium, FontWeight.Normal),
    Font(R.font.gill_sans_bold, FontWeight.Bold),
    Font(R.font.gill_sans_heavy, FontWeight.ExtraBold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = GillSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = GillSans,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),

    h2 = TextStyle(
        fontFamily = GillSans,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
package com.example.cstp2205_final.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cstp2205_final.theme.LightGreen
import com.example.cstp2205_final.theme.MainGreen
import com.example.cstp2205_final.theme.Pink
import com.example.cstp2205_final.view.composables.TopAddBT


@ExperimentalComposeUiApi
@Composable
fun MainScreen(navController: NavController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.End
        ) {
            TopAddBT(navController)
        }

        Spacer(modifier = Modifier.size(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "MY TREES",
                style = MaterialTheme.typography.h2,
                color = MainGreen
            )
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "",
                tint = MainGreen,
                modifier = Modifier.size(30.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "SHARED TREES",
                style = MaterialTheme.typography.h2,
                color = Pink
            )
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "",
                tint = Pink,
                modifier = Modifier.size(30.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "BUY A TREE",
                style = MaterialTheme.typography.h2,
                color = LightGreen
            )
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "",
                tint = LightGreen,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}
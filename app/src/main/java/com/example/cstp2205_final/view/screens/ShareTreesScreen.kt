package com.example.cstp2205_final.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cstp2205_final.theme.MainGreen

@Preview(showBackground = true)
@Composable
fun ShareTreesScreen(
//    navController: NavController,
//    auth: Auth,
//    userVM: UserViewModel = viewModel(factory = UserViewModelFactory(UserRepository()))
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(40.dp))

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                color = MainGreen,
                text = "SHARE TREES",
                fontSize = 32.sp,
                style = MaterialTheme.typography.h1,
            )
        }


//        OutlinedTextField(
//            value = type,
//            onValueChange = { type = it },
//            singleLine = true,
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Email,
//                imeAction = ImeAction.Next
//
//            ),
//            keyboardActions = KeyboardActions(
//                onNext = { focusRequester.requestFocus() }
//            ),
//            label = {
//                Text(
//                    "  type",
//                    fontFamily = GillSans,
//                    fontWeight = FontWeight.Normal,
//                )
//            },
//            leadingIcon = {
//                Image(
//                    painter = painterResource(id = R.drawable.tree_icon),
//                    contentDescription = "",
//                    modifier = Modifier
//                        .size(50.dp)
//                        .padding(2.dp)
//                )
//            },
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                backgroundColor = Color.White,
//                unfocusedBorderColor = SecondGreen,
//                focusedBorderColor = MainGreen,
//                unfocusedLabelColor = SecondGreen,
//                focusedLabelColor = MainGreen
//
//            ),
//            shape = RoundedCornerShape(10.dp),
//            modifier = Modifier.fillMaxWidth(0.7f)
//        )

    }
}
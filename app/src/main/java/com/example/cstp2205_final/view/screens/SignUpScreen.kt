package com.example.cstp2205_final.view.screens

import android.view.MotionEvent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cstp2205_final.R.drawable
import com.example.cstp2205_final.auth.Auth
import com.example.cstp2205_final.theme.GillSans
import com.example.cstp2205_final.theme.MainGreen
import com.example.cstp2205_final.theme.SecondGreen
import com.example.cstp2205_final.view.composables.ErrorMessage
import com.example.cstp2205_final.view.navigation.Screen

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SignUpScreen(navController: NavController, auth: Auth) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            signUpInput(navController, auth)
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun signUpInput(
    navController: NavController,
    auth: Auth
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }

    val (focusRequester,
        focusRequester1,
        focusRequester2) = FocusRequester.createRefs()
    val keyboardController = LocalSoftwareKeyboardController.current

    // Button animation
    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 0.95f else 1f)

    Column(
        modifier = Modifier
            .padding(top = 150.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "SIGN UP",
            style = MaterialTheme.typography.h1,
            fontWeight = FontWeight(600),
            color = MainGreen,
            fontSize = 35.sp

        )

        Spacer(modifier = Modifier.padding(25.dp))

        //Name input field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next

            ),
            keyboardActions = KeyboardActions(
                onNext = { focusRequester.requestFocus() }
            ),
            label = {
                Text(
                    "  name",
                    fontFamily = GillSans,
                    fontWeight = FontWeight.Normal,
                )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = drawable.tree_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(2.dp)
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = White,
                unfocusedBorderColor = SecondGreen,
                focusedBorderColor = MainGreen,
                unfocusedLabelColor = SecondGreen,
                focusedLabelColor = MainGreen

            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(0.7f)
        )

        Spacer(modifier = Modifier.padding(5.dp))

        //Email input field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next

            ),
            keyboardActions = KeyboardActions(
                onNext = { focusRequester1.requestFocus() }
            ),
            label = {
                Text(
                    "  email",
                    fontFamily = GillSans,
                    fontWeight = FontWeight.Normal,
                )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = drawable.tree_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(2.dp)
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = White,
                unfocusedBorderColor = SecondGreen,
                focusedBorderColor = MainGreen,
                unfocusedLabelColor = SecondGreen,
                focusedLabelColor = MainGreen

            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth(0.7f)
        )

        Spacer(modifier = Modifier.padding(5.dp))

        //Password input field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .focusRequester(focusRequester1),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusRequester2.requestFocus() }
            ),
            label = {
                Text(
                    "  password",
                    fontFamily = GillSans,
                    fontWeight = FontWeight.Normal,
                )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = drawable.tree_icon),
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)
                )
            },
            trailingIcon = {
                val image = if (passwordVisibility)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(imageVector = image, "")
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = White,
                unfocusedBorderColor = SecondGreen,
                focusedBorderColor = MainGreen,
                unfocusedLabelColor = SecondGreen,
                focusedLabelColor = MainGreen
            ),
            shape = RoundedCornerShape(10.dp),
        )

        Spacer(modifier = Modifier.padding(5.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .focusRequester(focusRequester2),
            visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            label = {
                Text(
                    "  confirm password",
                    fontFamily = GillSans,
                    fontWeight = FontWeight.Normal,
                )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = drawable.tree_icon),
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)
                )
            },
            trailingIcon = {
                val image = if (confirmPasswordVisibility)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = {
                    confirmPasswordVisibility = !confirmPasswordVisibility
                }) {
                    Icon(imageVector = image, "")
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = White,
                unfocusedBorderColor = SecondGreen,
                focusedBorderColor = MainGreen,
                unfocusedLabelColor = SecondGreen,
                focusedLabelColor = MainGreen
            ),
            shape = RoundedCornerShape(10.dp),
        )


        Spacer(modifier = Modifier.padding(30.dp))

        ErrorMessage(errorMessage, modifier = Modifier
            .fillMaxWidth(0.7f))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {


            Button(
                onClick = {
                     },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MainGreen
                ),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .scale(scale.value)
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selected.value = true
                                if(!password.contentEquals(confirmPassword)){
                                    errorMessage = "Passwords do not match"
                                }

                                auth.signUpWithEmailAndPassword(
                                    email,
                                    password,
                                    onError = {
                                        errorMessage = it
                                    },
                                    onSuccess = {
                                        navController.navigate(Screen.MainScreen.route)
                                    }
                                )
                            }
                            MotionEvent.ACTION_UP -> {
                                selected.value = false
                            }
                        }
                        true
                    }
            ) {
                Text(
                    color = White,
                    text = "sign up",
                    fontFamily = GillSans,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                )
            }
        }
    }
}
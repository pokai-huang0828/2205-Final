package com.example.cstp2205_final.view.screens

import android.view.MotionEvent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cstp2205_final.MainActivity
import com.example.cstp2205_final.R.drawable
import com.example.cstp2205_final.auth.Auth
import com.example.cstp2205_final.theme.Danger
import com.example.cstp2205_final.theme.GillSans
import com.example.cstp2205_final.theme.MainGreen
import com.example.cstp2205_final.theme.SecondGreen
import com.example.cstp2205_final.view.composables.ErrorMessage
import com.example.cstp2205_final.view.composables.LoginCheckBox
import com.example.cstp2205_final.view.composables.orSignInWith
import com.example.cstp2205_final.view.navigation.Screen

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SignInScreen(navController: NavController, auth: Auth) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            loginInput(navController, auth)
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun loginInput(
    navController: NavController,
    auth: Auth
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var displayErroMsg by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Button animation
    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 0.95f else 1f)

    val (focusRequester) = FocusRequester.createRefs()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .padding(top = 150.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "LOG IN",
            style = MaterialTheme.typography.h1,
            fontWeight = FontWeight(600),
            color = MainGreen,
            fontSize = 35.sp

        )

        Spacer(modifier = Modifier.padding(30.dp))

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
                onNext = { focusRequester.requestFocus() }
            ),
            label = {
                Text(
                    "  email address",
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

        //Password input field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .focusRequester(focusRequester),
            visualTransformation =
            if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
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

        if (displayErroMsg)
            Text(
                "Login failed, please try again.",
                color = Danger,
            )

        ErrorMessage(errorMessage, modifier = Modifier.fillMaxWidth(0.7f))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(top = 10.dp)
                .height(80.dp),
        ) {
            LoginCheckBox()
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = "Forget password?",
                    fontSize = 14.sp,
                    fontFamily = GillSans,
                    fontWeight = FontWeight.Normal,
                    color = SecondGreen,
                    modifier = Modifier
                        .clickable {  }
                )

                Spacer(modifier = Modifier.padding(5.dp))

                if (displayErroMsg)
                    Text(
                        "Login failed, please try again.",
                        color = Danger,
                    )

                ErrorMessage(errorMessage, modifier = Modifier.fillMaxWidth(0.7f))

                Button(
                    onClick = {  },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MainGreen
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .scale(scale.value)
                        .pointerInteropFilter {
                            when (it.action) {
                                MotionEvent.ACTION_DOWN -> {
                                    selected.value = true
                                    auth.signInWithEmailAndPassword(navController, email, password) {
                                        // error msg
                                        errorMessage = it
                                    }
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
                        text = "log in",
                        fontFamily = GillSans,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        orSignInWith()

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(80.dp),
        ) {
            Image(
                painter = painterResource(id = drawable.facebookicon),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {  }
            )

            Spacer(modifier = Modifier.size(10.dp))

            Image(
                painter = painterResource(id = drawable.googleicon),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .clickable { auth.signInWithGoogle(MainActivity.GOOGLE_AUTH) }
            )
        }

        Spacer(modifier = Modifier.padding(35.dp))

        Text(
            text =
            buildAnnotatedString {
                append("Don't have an account? Create one.")
                addStyle(
                    style = SpanStyle(
                        color = SecondGreen,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                    ),
                    start = 23,
                    end = 34
                )
            },
            fontSize = 14.sp,
            fontFamily = GillSans,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.clickable { navController.navigate(Screen.SignUpScreen.route) }
        )
    }
}
package com.example.cstp2205_final.auth

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.example.cstp2205_final.model.entities.User
import com.example.cstp2205_final.model.repositories.UserRepository
import com.example.cstp2205_final.model.responses.Resource
import com.example.cstp2205_final.view.navigation.Screen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Represents authentication with [Firebase] via Google and Login + Password
 */
class Auth(var context: Activity, default_web_client_id: String) {

    private var googleSignInClient: GoogleSignInClient
    private var _auth: FirebaseAuth = Firebase.auth
    var currentUser: FirebaseUser? = null

    init {
        // Initialize Firebase Auth
        currentUser = _auth.currentUser

        // Initialize gso
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(default_web_client_id)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    /**
     * Sign in with Google
     * @param [requestCode] [Request Code for startActivityForResult]
     */
    fun signInWithGoogle(requestCode: Int) {
        val signInIntent = googleSignInClient.signInIntent
        ActivityCompat.startActivityForResult(context, signInIntent, requestCode, null)
    }

    /**
     * Test if Google sign in has been successful. If Google sign in was successful,
     * it will call firebaseAuthWithGoogle, else it will display a Toast Error Message.
     * @param [data] [Data from signInWithGoogle Request]
     */
    @ExperimentalAnimationApi
    fun onGoogleSignInResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)

        try {
            val account = task.getResult(ApiException::class.java)!!

            // Google Sign In was successful, authenticate with Firebase
            firebaseAuthWithGoogle(account.idToken!!)

        } catch (e: ApiException) {
            // If sign in fails, display a message to the user.
            Toast.makeText(
                context,
                "Authentication failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * This private function is called when Google Sign In was successful.
     * Id Token will be used to authenticate user to the app.
     * @param [idToken] [Token returned from Google Sign In]
     */
    @ExperimentalAnimationApi
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        _auth.signInWithCredential(credential)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    val userRepo = UserRepository()
                    currentUser = _auth.currentUser

                    // Try to get user from firestore
                    CoroutineScope(Dispatchers.IO).launch {
                        userRepo.getUserById(_auth.currentUser?.uid!!) { response ->
                            // if no user, create one
                            if (response is Resource.Error) {
                                userRepo.createUser(
                                    User(
                                        email = _auth.currentUser?.email!!,
                                        id = _auth.currentUser?.uid!!
                                    )
                                ) {
                                    // check response type here
                                }
                            }
                        }
                    }

                    // Restart the app without transition
                    context.finish()
                    context.startActivity(context.intent)
                    context.overridePendingTransition(0, 0)
                } else {
                    currentUser = null
                }
            }
    }

    /**
     * Sign up new user with email and password
     * @param [navController] [used to navigate user to another screen]
     * @param [email] [user email]
     * @param [password] [user password]
     */
    @ExperimentalAnimationApi
    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        onError: (errorMessage: String) -> Unit,
        onSuccess: () -> Unit,
    ) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            _auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(context) { task ->
                    if (task.isSuccessful) {
                        currentUser = _auth.currentUser

                        // Save new user to firestore
                        val userRepo = UserRepository()

                        userRepo.createUser(
                            User(
                                email = _auth.currentUser?.email!!,
                                id = _auth.currentUser?.uid!!
                            )
                        ) {
                            if (it is Resource.Success<*>) {
                                // Sign up and Save user successfully, update UI
                                onSuccess()
                            } else {
                                onError(it.message ?: "Please try again.")
                            }
                        }
                    }
                }
                .addOnFailureListener {
                    onError(it.message ?: "Please try again.")
                }
        }
    }

    /**
     * Sign in with email and password
     * @param [navController] [used to navigate user to another screen]
     * @param [email] [user email]
     * @param [password] [user password]
     * @param [onError] [callback called when sign-in fails]
     */
    @ExperimentalAnimationApi
    fun signInWithEmailAndPassword(
        navController: NavController,
        email: String,
        password: String,
        onError: (errorMessage: String) -> Unit,
    ) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            _auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(context) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI
                        currentUser = _auth.currentUser

                        navController.navigate(Screen.MainScreen.route)
                    }
                }.addOnFailureListener {
                    // If sign in fails, display a message to the user.
                    onError(it?.message ?: "Please try again.")
                }
        }
    }

    /**
     * Sign out from the app
     * @param [navController] [used to navigate user to another screen]
     */
    fun signOut(navController: NavController) {
        _auth.signOut()
        currentUser = null

        navController.navigate(Screen.SignInScreen.route)
    }
}
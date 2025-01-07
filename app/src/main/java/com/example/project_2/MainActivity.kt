package com.example.project_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project_2.presentation.TwitterSignUpScreen
import com.example.project_2.ui.theme.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.*
import com.example.project_2.presentation.ForgetPassword
import com.example.project_2.presentation.TwitterLoginScreen


class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContent {
            val systemUiController = rememberSystemUiController()
            AppTheme {
                val statusBarColor = MaterialTheme.colorScheme.primary
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = statusBarColor, darkIcons = false
                    )
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController, startDestination = "TwitterSignUpScreen"
                        ) {
                            composable("TwitterSignUpScreen") {
                                TwitterSignUpScreen(
                                    navController = navController,
                                    auth = auth
                                )
                            }
                            composable("loginScreen") {
                                TwitterLoginScreen(navController = navController, auth = auth)
                            }
                            composable("forgetPassword") {
                                ForgetPassword(navController=navController,auth=auth)
                            }
                        }
                    }
                }
            }
        }
    }
}


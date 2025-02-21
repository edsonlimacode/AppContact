package com.edsonlimadev.appcontact

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.edsonlimadev.appcontact.ui.navigation.host.NavHostScreen
import com.edsonlimadev.appcontact.ui.theme.AppContactTheme
import com.edsonlimadev.appcontact.utils.getCurrentUser
import com.edsonlimadev.appcontact.utils.getUserUid
import com.edsonlimadev.appcontact.utils.isUserLoggedIn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            Log.i(
                "user",
                "id: ${getUserUid()} logged: ${isUserLoggedIn()} current: ${getCurrentUser().currentUser?.email}"
            )

            AppContactTheme {
                NavHostScreen(navController)
            }
        }
    }
}
package com.edsonlimadev.appcontact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.edsonlimadev.appcontact.ui.navigation.host.MainNavHost
import com.edsonlimadev.appcontact.ui.theme.AppContactTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AppContactTheme(
                darkTheme = false
            ) {
                MainNavHost()
            }
        }
    }
}
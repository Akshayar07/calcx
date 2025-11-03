package com.example.calcx

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.calcx.ui.theme.CalcxTheme
import com.example.calcx.ui.theme.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalcxApp()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun CalcxApp() {
    CalcxTheme {
        val navController = rememberNavController()
        AppNavHost(navController = navController)
    }
}










package dev.shevikina.surfspringschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dev.shevikina.surfspringschool.presentation.screens.MainNavScreen
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SurfSpringSchoolTheme {
                MainNavScreen(rememberNavController())
            }
        }
    }
}

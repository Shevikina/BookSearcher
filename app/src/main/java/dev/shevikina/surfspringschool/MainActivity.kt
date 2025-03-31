package dev.shevikina.surfspringschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.shevikina.surfspringschool.presentation.screens.MainNavScreen
import dev.shevikina.surfspringschool.presentation.screens.data.FavoriteState
import dev.shevikina.surfspringschool.presentation.screens.components.FavoriteSnackbars
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
            val snackState = remember { mutableStateOf(FavoriteState.NONE) }

            SurfSpringSchoolTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(snackbarHostState) { data ->
                            snackState.value = when (data.visuals.message) {
                                FavoriteState.GOOD_ADDED.toString() -> FavoriteState.GOOD_ADDED
                                FavoriteState.GOOD_REMOVE.toString() -> FavoriteState.GOOD_REMOVE
                                FavoriteState.BAD_ADDED.toString() -> FavoriteState.BAD_ADDED
                                FavoriteState.BAD_REMOVE.toString() -> FavoriteState.BAD_REMOVE
                                else -> FavoriteState.NONE
                            }

                            FavoriteSnackbars(snackState.value) { data.dismiss() }
                        }
                    }
                ) { innerPadding ->
                    MainNavScreen(
                        navController = navController,
                        snackbarHostState = snackbarHostState,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

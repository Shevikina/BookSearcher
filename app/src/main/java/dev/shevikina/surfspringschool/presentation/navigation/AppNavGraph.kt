package dev.shevikina.surfspringschool.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.shevikina.surfspringschool.presentation.screens.search.SearchMainScreen

@Composable
fun AppNavigationGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.Search,
        modifier = modifier.fillMaxSize()
    ) {
        composable<Route.Search> {
            SearchMainScreen(snackbarHostState)
        }
        composable<Route.Favorites> {
            //TODO: Создание основного окна с открытой вкладкой избранного
        }
        composable<Route.Details> {
            //TODO: Доп окно с деталями
        }
    }
}
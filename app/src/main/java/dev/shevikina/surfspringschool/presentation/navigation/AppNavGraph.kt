package dev.shevikina.surfspringschool.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.Search,
        modifier = modifier.fillMaxSize()
    ) {
        composable<Route.Search> {
            //TODO: Создание основного окна с открытой вкладкой поиска
        }
        composable<Route.Favorites> {
            //TODO: Создание основного окна с открытой вкладкой избранного
        }
        composable<Route.Details> {
            //TODO: Доп окно с деталями
        }
    }
}
package dev.shevikina.surfspringschool.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.shevikina.surfspringschool.domain.models.BookModel
import dev.shevikina.surfspringschool.presentation.screens.description.DescriptionScreen
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
            SearchMainScreen(
                snackbarHostState = snackbarHostState,
                onCardClicked = { info ->
                    navController.navigate(
                        Route.Details(
                            info.id,
                            info.author,
                            info.title,
                            info.imageUrl,
                            info.description,
                            info.publishedDate
                        )
                    )
                }
            )
        }
        composable<Route.Favorites> {
            //TODO: Создание основного окна с открытой вкладкой избранного
        }
        composable<Route.Details> { backStackEntry ->
            val page = backStackEntry.toRoute<Route.Details>()
            DescriptionScreen(
                BookModel(
                    page.id,
                    page.author,
                    page.title,
                    page.imageUrl,
                    page.description,
                    page.publishedDate
                ),
                onBackClicked = { navController.popBackStack() }
            )
        }
    }
}
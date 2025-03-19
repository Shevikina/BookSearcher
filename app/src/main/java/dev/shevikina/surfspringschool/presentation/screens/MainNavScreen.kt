package dev.shevikina.surfspringschool.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.shevikina.surfspringschool.R
import dev.shevikina.surfspringschool.presentation.navigation.AppNavigationGraph
import dev.shevikina.surfspringschool.presentation.navigation.Route
import dev.shevikina.surfspringschool.presentation.screens.components.NavBar
import dev.shevikina.surfspringschool.presentation.screens.data.BottomNavItemInfo
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme


@Composable
fun MainNavScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val navigationBarItems = remember {
        listOf(
            BottomNavItemInfo(Route.Search, R.drawable.search_icon, "Поиск"),
            BottomNavItemInfo(Route.Favorites, R.drawable.favorite_icon, "Избранное")
        )
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = navigationBarItems.any { screen ->
        currentDestination?.route == screen.route::class.qualifiedName
    }

    Column(modifier = modifier) {
        Box(modifier = Modifier.weight(1f)) { AppNavigationGraph(navController) }
        if (bottomBarDestination) {
            HorizontalDivider()
            NavBar(
                navItemsInfo = navigationBarItems,
                currentDestination = currentDestination,
                onItemClick = remember {
                    { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    SurfSpringSchoolTheme {
        MainNavScreen(rememberNavController())
    }
}
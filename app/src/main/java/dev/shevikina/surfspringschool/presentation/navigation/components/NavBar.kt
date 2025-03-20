package dev.shevikina.surfspringschool.presentation.navigation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.shevikina.surfspringschool.R
import dev.shevikina.surfspringschool.presentation.navigation.Route
import dev.shevikina.surfspringschool.presentation.screens.data.BottomNavItemInfo
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme


@Composable
fun NavBar(
    navItemsInfo: List<BottomNavItemInfo>,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
    onItemClick: (route: Route) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        navItemsInfo.forEachIndexed { idx, screenInfo ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.route == screenInfo.route::class.qualifiedName
            } == true

            NavItem(
                iconId = screenInfo.iconId,
                text = screenInfo.text,
                selected = isSelected,
                modifier = Modifier.weight(1f / navItemsInfo.count().toFloat()),
                onClick = { onItemClick(screenInfo.route) }
            )
        }
    }
}

@Preview
@Composable
private fun NavBarPreview() {
    SurfSpringSchoolTheme {
        val navigationBarItems =
            listOf(
                BottomNavItemInfo(Route.Search, R.drawable.search_icon, "Поиск"),
                BottomNavItemInfo(Route.Favorites, R.drawable.favorite_icon, "Избранное")
            )

        val navBackStackEntry by rememberNavController().currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        NavBar(
            navItemsInfo = navigationBarItems,
            currentDestination = currentDestination,
            onItemClick = remember {
                { /*route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }*/
                }
            }
        )
    }
}
package dev.shevikina.surfspringschool.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Search : Route()

    @Serializable
    data object Favorites : Route()

    @Serializable
    data class Details(
        val author: String,
        val title: String,
        val imageUrl: String,
        val description: String,
        val publishedDate: String
    ) : Route()
}

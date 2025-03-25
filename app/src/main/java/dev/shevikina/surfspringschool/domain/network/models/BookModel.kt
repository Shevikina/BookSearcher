package dev.shevikina.surfspringschool.domain.network.models

data class BookModel(
    val id: String,
    val author: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    val publishedDate: String
)

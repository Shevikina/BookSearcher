package dev.shevikina.surfspringschool.domain.models

data class BookModel(
    val id: String,
    val authors: String,
    val title: String,
    val imageUrl: String,
    val description: String
)

package dev.shevikina.surfspringschool.domain.models

data class BookModel(
    val id: String,
    val author: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    val publishedDate: String
)

class InvalidBookModelException(message: String) : Exception(message)
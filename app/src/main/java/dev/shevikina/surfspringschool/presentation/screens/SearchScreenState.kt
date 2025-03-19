package dev.shevikina.surfspringschool.presentation.screens

import dev.shevikina.surfspringschool.domain.models.BookModel

data class SearchScreenState(
    val bookList: List<BookModel> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

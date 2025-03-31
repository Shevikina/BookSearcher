package dev.shevikina.surfspringschool.presentation.screens

import dev.shevikina.surfspringschool.domain.models.BookModel

data class ScreenState(
    val searchValue: String = "",
    val bookList: List<BookModel> = emptyList(),
    val favoriteBookList: List<BookModel> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

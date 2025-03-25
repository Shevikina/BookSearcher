package dev.shevikina.surfspringschool.presentation.screens.search

import dev.shevikina.surfspringschool.domain.network.models.BookModel

data class SearchScreenState(
    val bookList: List<BookModel> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

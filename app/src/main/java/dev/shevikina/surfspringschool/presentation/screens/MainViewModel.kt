package dev.shevikina.surfspringschool.presentation.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.shevikina.surfspringschool.domain.db.use_case.BookUseCases
import dev.shevikina.surfspringschool.domain.models.BookModel
import dev.shevikina.surfspringschool.domain.network.usecase.GetBooksByTitleUseCase
import dev.shevikina.surfspringschool.presentation.utils.handle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBooksByTitleUseCase: GetBooksByTitleUseCase,
    private val dbBookUseCases: BookUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            updateStateFavoriteBooksList()
        }
    }

    suspend fun updateStateFavoriteBooksList() {
        _uiState.update { state ->
            state.copy(
                favoriteBookList = dbBookUseCases.getBooksUseCase.invoke().first()
            )
        }
    }

    fun clear() {
        _uiState.update { state ->
            state.copy(
                searchValue = "",
                isLoading = true,
                bookList = emptyList(),
                errorMessage = null
            )
        }
    }

    fun updateSearch(text: String) {
        _uiState.update { state ->
            state.copy(
                searchValue = text
            )
        }
    }

    fun getAllSearchedBooks(value: String) {
        _uiState.update { state ->
            state.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            getBooksByTitleUseCase(value).handle(
                onSuccess = { books ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            bookList = books,
                            errorMessage = null
                        )
                    }
                    viewModelScope.launch {
                        updateStateFavoriteBooksList()
                    }
                },
                onError = ::onError
            )
        }
    }

    suspend fun addFavoriteBook(book: BookModel) {
        try {
            if (getFavoriteBook(book.id) == null) {
                dbBookUseCases.addBookUseCase.invoke(book)
                updateStateFavoriteBooksList()
            }
        } catch (e: Exception) {
            Log.e("DB", e.message ?: "Unspecified error")
        }
    }

    suspend fun removeFavoriteBook(book: BookModel) {
        try {
            if (getFavoriteBook(book.id) != null) {
                dbBookUseCases.deleteBookUseCase.invoke(book.id)
                updateStateFavoriteBooksList()
            }
        } catch (e: Exception) {
            Log.e("DB", e.message ?: "Unspecified error")
        }
    }

    suspend fun getFavoriteBook(networkId: String): BookModel? =
        dbBookUseCases.getBookUseCase.invoke(networkId)

    private fun onError(message: String) {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                bookList = emptyList(),
                errorMessage = message
            )
        }
    }
}


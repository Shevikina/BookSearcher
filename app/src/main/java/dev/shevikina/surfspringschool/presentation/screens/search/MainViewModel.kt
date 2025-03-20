package dev.shevikina.surfspringschool.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.shevikina.surfspringschool.domain.usecase.GetBooksByTitleUseCase
import dev.shevikina.surfspringschool.presentation.utils.handle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBooksByTitleUseCase: GetBooksByTitleUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchScreenState())
    val uiState = _uiState.asStateFlow()

    fun clear() {
        _uiState.update { state ->
            state.copy(
                isLoading = true,
                bookList = emptyList(),
                errorMessage = null
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
                },
                onError = ::onError
            )
        }
    }

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
package dev.shevikina.surfspringschool.presentation.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.shevikina.surfspringschool.domain.models.BookModel
import dev.shevikina.surfspringschool.presentation.screens.MainViewModel
import dev.shevikina.surfspringschool.presentation.screens.ScreenState
import dev.shevikina.surfspringschool.presentation.screens.components.MainScreenSuccess
import dev.shevikina.surfspringschool.presentation.screens.data.FavoriteState
import dev.shevikina.surfspringschool.presentation.screens.search.components.SearchScreenRetry
import dev.shevikina.surfspringschool.presentation.screens.search.components.SearchTextField
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@Composable
fun SearchMainScreen(
    snackBarHostState: SnackbarHostState,
    onCardClicked: (info: BookModel) -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val state: State<ScreenState> = mainViewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        queryState = state.value,
        onCardClicked = onCardClicked,
        searchQuery = state.value.searchValue,
        sendQuery = { text -> mainViewModel::getAllSearchedBooks.invoke(text) },
        onValueChanged = { text ->
            if (text.isEmpty()) mainViewModel::clear.invoke()
            else mainViewModel::updateSearch.invoke(text)
        },
        isFavoriteBook = { book ->
            runBlocking {
                CoroutineScope(Dispatchers.IO).async {
                    mainViewModel::getFavoriteBook.invoke(book.id) != null
                }.await()
            }
        },
        onMarkChanged = { isMark, book, callback ->
            try {
                CoroutineScope(Dispatchers.Main).launch {
                    if (isMark) {
                        withContext(Dispatchers.IO) {
                            mainViewModel::addFavoriteBook.invoke(book)
                        }
                        withContext(Dispatchers.Default) {
                            callback(state.value.favoriteBookList.contains(book))
                            snackBarHostState.showSnackbar(
                                message = if (state.value.favoriteBookList.contains(book)) FavoriteState.GOOD_ADDED.toString()
                                else FavoriteState.BAD_ADDED.toString(),
                                withDismissAction = true
                            )
                        }
                    } else {
                        withContext(Dispatchers.IO) {
                            mainViewModel::removeFavoriteBook.invoke(book)
                        }
                        withContext(Dispatchers.Default) {
                            callback(!state.value.favoriteBookList.contains(book))
                            snackBarHostState.showSnackbar(
                                message = if (state.value.favoriteBookList.contains(book)) FavoriteState.BAD_REMOVE.toString()
                                else FavoriteState.GOOD_REMOVE.toString(),
                                withDismissAction = true
                            )
                        }
                    }
                }
            } catch (e: Throwable) {
                Log.e("DB", e.message ?: "unknown error")
            }
        }
    )
}

@Composable
private fun SearchScreen(
    queryState: ScreenState,
    searchQuery: String,
    sendQuery: (value: String) -> Unit,
    onValueChanged: (value: String) -> Unit,
    isFavoriteBook: (info: BookModel) -> Boolean,
    onMarkChanged: (marked: Boolean, book: BookModel, isSuccessCallback: (Boolean) -> Unit) -> Unit,
    onCardClicked: (info: BookModel) -> Unit
) {
    val errorMessage = queryState.errorMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        SearchTextField(
            searchQuery = searchQuery,
            sendQuery = sendQuery,
            onValueChanged = onValueChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        if (searchQuery.isEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            )
            {
                Text(
                    text = "Введите название книги,\nкоторую ищете",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 35.dp)
                )
            }
        } else {
            when {
                queryState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onBackground,
                            strokeWidth = 1.5.dp
                        )
                    }
                }

                errorMessage != null -> {
                    when {
                        errorMessage == "400" -> {
                            NotFoundText()
                        }

                        errorMessage.contains("is empty") -> {
                            NotFoundText()
                        }

                        else -> {
                            SearchScreenRetry { sendQuery(searchQuery) }
                        }
                    }
                }

                else -> {
                    MainScreenSuccess(
                        books = queryState.bookList,
                        isFavoriteBook = isFavoriteBook,
                        onMarkChanged = onMarkChanged,
                        onCardClicked = onCardClicked
                    )
                }
            }
        }
    }
}

@Composable
private fun NotFoundText() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "По вашему запросу ничего не найдено",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(apiLevel = 34)
@Composable
private fun SearchScreenPreview() {
    SurfSpringSchoolTheme {
        SearchScreen(
            queryState = ScreenState(),
            searchQuery = "",
            onValueChanged = {},
            sendQuery = {},
            isFavoriteBook = { _ -> false },
            onMarkChanged = { _, _, _ -> },
            onCardClicked = {}
        )
    }
}


package dev.shevikina.surfspringschool.presentation.screens.search

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.shevikina.surfspringschool.domain.models.BookModel
import dev.shevikina.surfspringschool.presentation.screens.data.FavoriteState
import dev.shevikina.surfspringschool.presentation.screens.search.components.MainScreenSuccess
import dev.shevikina.surfspringschool.presentation.screens.search.components.SearchScreenRetry
import dev.shevikina.surfspringschool.presentation.screens.search.components.SearchTextField
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme
import kotlinx.coroutines.launch

@Composable
fun SearchMainScreen(
    snackbarHostState: SnackbarHostState,
    onCardClicked: (info:BookModel)->Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val state: State<SearchScreenState> = mainViewModel.uiState.collectAsStateWithLifecycle()
    val markScope = rememberCoroutineScope()

    SearchScreen(
        queryState = state.value,
        onCardClicked = onCardClicked,
        sendQuery = { text -> mainViewModel::getAllSearchedBooks.invoke(text) },
        onValueChanged = { text -> if (text.isEmpty()) mainViewModel::clear.invoke() },
        onMarkChanged = { isMark ->
            markScope.launch {
                if (isMark)
                    snackbarHostState.showSnackbar(
                        message = FavoriteState.GOOD_ADDED.toString(),
                        withDismissAction = true
                    )
                else
                    snackbarHostState.showSnackbar(
                        message = FavoriteState.GOOD_REMOVE.toString(),
                        withDismissAction = true
                    )
            }
        }
    )
}

@Composable
private fun SearchScreen(
    queryState: SearchScreenState,
    sendQuery: (value: String) -> Unit,
    onValueChanged: (value: String) -> Unit,
    onMarkChanged: (marked: Boolean) -> Unit,
    onCardClicked: (info:BookModel)->Unit
) {
    val errorMessage = queryState.errorMessage
    val searchQuery = remember { mutableStateOf("") }

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
        if (searchQuery.value.isEmpty()) {
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
                            SearchScreenRetry { sendQuery(searchQuery.value) }
                        }
                    }
                }

                else -> {
                    MainScreenSuccess(
                        queryState.bookList,
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
            queryState = SearchScreenState(),
            onValueChanged = {},
            sendQuery = {},
            onMarkChanged = {},
            onCardClicked = {}
        )
    }
}


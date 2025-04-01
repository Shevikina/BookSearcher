package dev.shevikina.surfspringschool.presentation.screens.favorite

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import dev.shevikina.surfspringschool.presentation.screens.favorite.components.FavoriteScreenTopBar
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun FavoriteMainScreen(
    snackBarHostState: SnackbarHostState,
    onCardClicked: (info: BookModel) -> Unit,
    onBackClicked: () -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val screenContext = LocalContext.current
    val state: State<ScreenState> = mainViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state.value.favoriteBookList) {
        CoroutineScope(Dispatchers.IO).launch {
            mainViewModel::updateStateFavoriteBooksList.invoke()
        }
    }

    FavoriteScreen(
        queryState = state.value,
        onCardClicked = onCardClicked,
        onBackClicked = onBackClicked,
        onTitleClicked = {
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    mainViewModel::updateStateFavoriteBooksList.invoke()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            screenContext,
                            "Список избранных книг обновлен",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } catch (e: Throwable) {
                Log.e("DB", e.message ?: "unknown error")
            }
        },
        onMarkChanged = { isMark, book, callback ->
            try {
                if (!isMark) {
                    CoroutineScope(Dispatchers.Main).launch {
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
private fun FavoriteScreen(
    queryState: ScreenState,
    onCardClicked: (info: BookModel) -> Unit,
    onBackClicked: () -> Unit,
    onTitleClicked: () -> Unit,
    onMarkChanged: (marked: Boolean, book: BookModel, isSuccessCallback: (Boolean) -> Unit) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FavoriteScreenTopBar(
            onTitleClicked = onTitleClicked,
            onBackClicked = onBackClicked,
            modifier = Modifier.height(48.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            if (queryState.favoriteBookList.isEmpty()) {
                Text(
                    text = "Нет ни одной избранной книги",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 35.dp)
                )
            } else {
                MainScreenSuccess(
                    modifier = Modifier.fillMaxSize(),
                    books = queryState.favoriteBookList,
                    isFavoriteBook = { true },
                    onMarkChanged = onMarkChanged,
                    onCardClicked = onCardClicked
                )
            }
        }
    }

}

@Preview
@Composable
private fun FavoriteScreenPreview() {
    SurfSpringSchoolTheme {
        FavoriteScreen(
            queryState = ScreenState(),
            onCardClicked = {},
            onBackClicked = {},
            onTitleClicked = {},
            onMarkChanged = { _, _, _ -> }
        )
    }
}
package dev.shevikina.surfspringschool.presentation.screens.description

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import dev.shevikina.surfspringschool.domain.models.BookModel
import dev.shevikina.surfspringschool.presentation.screens.description.components.DescriptionScreenTopBar
import dev.shevikina.surfspringschool.presentation.screens.MainViewModel
import dev.shevikina.surfspringschool.presentation.screens.ScreenState
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@Composable
fun DescriptionScreen(
    info: BookModel,
    mainViewModel: MainViewModel = hiltViewModel(),
    onBackClicked: () -> Unit,
) {
    val state: State<ScreenState> = mainViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DescriptionScreenTopBar(
            modifier = Modifier.height(48.dp),
            onBackClicked = onBackClicked,
            isFavorite = runBlocking {
                CoroutineScope(Dispatchers.IO).async {
                    mainViewModel::getFavoriteBook.invoke(info.id) != null
                }.await()
            },
            onMarkChanged = { isMark, callback ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        if (isMark)
                            mainViewModel::addFavoriteBook.invoke(info)
                        else
                            mainViewModel::removeFavoriteBook.invoke(info)

                        withContext(Dispatchers.Default) {
                            callback(state.value.favoriteBookList.contains(info) == isMark)
                        }
                    } catch (e: Throwable) {
                        Log.e("DB", e.message ?: "unknown error")
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        AsyncImage(
            model = info.imageUrl,
            contentDescription = info.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp, 300.dp)
                .background(Color.Gray, RoundedCornerShape(24.dp))
                .clip(RoundedCornerShape(24.dp))
        )
        Spacer(modifier = Modifier.height(14.dp))
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                info.author,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.surface
            )
            Text(
                info.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium.copy(lineHeight = 1.em),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                info.publishedDate,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.surface
            )
        }
        Spacer(modifier = Modifier.height(22.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
                .fillMaxSize()
                .weight(1f)
                .padding(20.dp)
        ) {
            Text(
                "Описание",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                info.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
private fun DescriptionScreenPreview() {
    SurfSpringSchoolTheme {
        DescriptionScreen(
            info = BookModel(
                title = "Тайная исповедь в православной восточной церкви",
                author = "А.Н. Алмазов",
                description = "Опыт внешней истории. Исследование преимущественно по рукописям.",
                id = "y1MGAwAAQBAJ",
                imageUrl = "https://books.google.com/books/content?id=y1MGAwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                publishedDate = "2024 г."
            ),
            onBackClicked = {}
        )
    }
}
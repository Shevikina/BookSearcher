package dev.shevikina.surfspringschool.presentation.screens.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.shevikina.surfspringschool.domain.models.BookModel
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme

@Composable
fun MainScreenSuccess(
    books: List<BookModel>,
    modifier: Modifier = Modifier,
    isFavoriteBook: (info: BookModel) -> Boolean,
    onMarkChanged: (marked: Boolean, book: BookModel) -> Boolean,
    onCardClicked: (info: BookModel) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalItemSpacing = 32.62.dp,
        modifier = modifier
    ) {
        items(books) { info ->
            BookCard(
                info = info,
                modifier = Modifier.height(290.dp),
                isFavoriteBook = isFavoriteBook,
                onMarkChanged = onMarkChanged,
                onCardClicked = onCardClicked
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenSuccessPreview() {
    SurfSpringSchoolTheme {
        Box(Modifier.padding(20.dp)) {
            MainScreenSuccess(
                listOf(
                    BookModel("1", "22", "33", "44", "55", ""),
                    BookModel("1", "22", "33", "44", "55", ""),
                    BookModel("1", "22", "33", "44", "55", ""),
                ),
                isFavoriteBook = { false },
                onMarkChanged = { _, _ -> true },
                onCardClicked = { }
            )
        }
    }
}
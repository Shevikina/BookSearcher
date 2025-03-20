package dev.shevikina.surfspringschool.presentation.screens.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.shevikina.surfspringschool.domain.models.BookModel
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme

@Composable
fun MainScreenSuccess(
    books: List<BookModel>,
    modifier: Modifier = Modifier
) {
    //ЗАГЛУШКА
    Column(modifier = modifier.verticalScroll(rememberScrollState(0))) {
        books.forEach { info ->
            Text("${info.id}\n${info.title}\n${info.authors}\n${info.description}\n${info.imageUrl}")
            HorizontalDivider()
        }
    }
}

@Preview
@Composable
private fun MainScreenSuccessPreview() {
    SurfSpringSchoolTheme {
        MainScreenSuccess(
            listOf(
                BookModel("1", "22", "33", "44", "55"),
                BookModel("1", "22", "33", "44", "55"),
                BookModel("1", "22", "33", "44", "55"),
            )
        )
    }
}
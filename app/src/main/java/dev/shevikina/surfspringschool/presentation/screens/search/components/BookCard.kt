package dev.shevikina.surfspringschool.presentation.screens.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.compose.AsyncImage
import dev.shevikina.surfspringschool.domain.models.BookModel
import dev.shevikina.surfspringschool.presentation.screens.components.FavoriteIcon
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme

@Composable
fun BookCard(
    info: BookModel,
    modifier: Modifier = Modifier,
    onMarkChanged: (marked: Boolean) -> Unit
) {
    Box(modifier = modifier) {
        Column(modifier = modifier) {
            AsyncImage(
                model = info.imageUrl,
                contentDescription = info.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(230.dp)
                    .fillMaxWidth()
                    .background(Color.Gray, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.surface)) { append(info.author) }
                    append("\n")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                        append(
                            info.title
                        )
                    }
                },
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 1.2.em)
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.55.dp, end = 9.dp),
            contentAlignment = Alignment.TopEnd
        ) { FavoriteIcon(onMarkChanged) }
    }
}

@Preview
@Composable
private fun BookCardPreview() {
    SurfSpringSchoolTheme {
        BookCard(
            BookModel(
                title = "Тайная исповедь в православной восточной церкви",
                author = "А.Н. Алмазов",
                description = "Опыт внешней истории. Исследование преимущественно по рукописям.",
                id = "y1MGAwAAQBAJ",
                imageUrl = "https://books.google.com/books/content?id=y1MGAwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
            ),
            modifier = Modifier.size(154.dp, 289.38.dp)
        ) {}
    }
}
package dev.shevikina.surfspringschool.presentation.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.shevikina.surfspringschool.presentation.screens.data.FavoriteState
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme

@Composable
fun FavoriteSnackbars(snackType: FavoriteState, onDismissClicked: () -> Unit) {
    Snackbar(
        contentColor = MaterialTheme.colorScheme.background,
        dismissActionContentColor = MaterialTheme.colorScheme.background,
        containerColor = if (snackType in arrayOf(
                FavoriteState.BAD_ADDED,
                FavoriteState.BAD_REMOVE
            )
        ) MaterialTheme.colorScheme.tertiary
        else MaterialTheme.colorScheme.onSecondary,
        shape = RoundedCornerShape(12.dp),
        dismissAction = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd) {
                IconButton(
                    onClick = onDismissClicked
                ) { Icon(Icons.Default.Close, null) }
            }
        },
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .fillMaxWidth()
            .height(60.dp)
            .offset(y = (-80).dp)
    ) {
        val text = when (snackType) {
            FavoriteState.NONE -> "Этого тоста тут быть не должно"
            FavoriteState.GOOD_ADDED -> "Книга успешно добавлена в избранное"
            FavoriteState.GOOD_REMOVE -> "Книга успешно удалена из избранного"
            FavoriteState.BAD_ADDED -> "Ошибка добавления в избранное"
            FavoriteState.BAD_REMOVE -> "Ошибка удаления избранной книги"
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun GoodAddFavoriteSnackbarsPreview() {
    SurfSpringSchoolTheme {
        FavoriteSnackbars(FavoriteState.GOOD_ADDED) {}
    }
}

@Preview
@Composable
private fun BadAddFavoriteSnackbarsPreview() {
    SurfSpringSchoolTheme {
        FavoriteSnackbars(FavoriteState.BAD_ADDED) {}
    }
}
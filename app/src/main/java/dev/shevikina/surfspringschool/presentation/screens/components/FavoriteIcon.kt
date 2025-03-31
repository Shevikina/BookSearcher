package dev.shevikina.surfspringschool.presentation.screens.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.shevikina.surfspringschool.R
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme

@Composable
fun FavoriteIcon(
    isFavorite: Boolean = false,
    onMarkChanged: (marked: Boolean) -> Boolean
) {
    var marked by remember { mutableStateOf(isFavorite) }
    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.favorite_icon),
        contentDescription = "Click on the heart to add this book to your favorites",
        tint = if (marked) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .shadow(3.dp, CircleShape, ambientColor = Color.Black.copy(0.1f))
            .background(MaterialTheme.colorScheme.background, CircleShape)
            .clip(CircleShape)
            .clickable {
                val isSuccessChange = onMarkChanged(!marked)
                Log.d("MyLog", "\tisSuccessChange $isSuccessChange")
//                if (isSuccessChange) //TODO: исправить onMarkChanged и раскоментить
                marked = !marked
            }
            .size(24.dp)
            .padding(horizontal = 4.dp, vertical = 3.97.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun FavIcPreview() {
    SurfSpringSchoolTheme {
        FavoriteIcon(
            isFavorite = false,
            onMarkChanged = { _ -> true }
        )
    }
}
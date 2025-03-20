package dev.shevikina.surfspringschool.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    onMarkChanged: (marked: Boolean) -> Unit
){
    val marked = remember { mutableStateOf(false) }
    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.favorite_icon),
        contentDescription = "Click on the heart to add this book to your favorites",
        tint = if (marked.value) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .shadow(3.dp, CircleShape, ambientColor = Color.Black.copy(0.1f))
            .background(MaterialTheme.colorScheme.background, CircleShape)
            .clip(CircleShape)
            .clickable {
                marked.value = !marked.value
                onMarkChanged(marked.value)
            }
            .size(24.dp)
            .padding(horizontal = 4.dp, vertical = 3.97.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun FavIcPreview() {
    SurfSpringSchoolTheme {
        FavoriteIcon{}
    }
}
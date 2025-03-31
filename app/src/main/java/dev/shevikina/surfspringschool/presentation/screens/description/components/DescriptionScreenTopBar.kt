package dev.shevikina.surfspringschool.presentation.screens.description.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.shevikina.surfspringschool.presentation.screens.components.FavoriteIcon
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme

@Composable
fun DescriptionScreenTopBar(
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
    onMarkChanged: (marked: Boolean, isSuccessCallback: (Boolean) -> Unit) -> Unit,
    onBackClicked: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 20.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onBackClicked)
        )
        Spacer(modifier = Modifier.weight(1f))
        FavoriteIcon(
            isFavorite = isFavorite,
            onMarkChanged = onMarkChanged
        )
    }
}

@Preview
@Composable
private fun DescriptionScreenAppBarPreview() {
    SurfSpringSchoolTheme {
        DescriptionScreenTopBar(
            modifier = Modifier,
            isFavorite = false,
            onMarkChanged = { _, _ -> },
            onBackClicked = {}
        )
    }
}
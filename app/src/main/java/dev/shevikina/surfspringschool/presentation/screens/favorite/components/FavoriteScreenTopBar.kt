package dev.shevikina.surfspringschool.presentation.screens.favorite.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme

@Composable
fun FavoriteScreenTopBar(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onTitleClicked: () -> Unit
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
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            text = "Избранное",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.clickable(onClick = onTitleClicked)
        )
        Spacer(modifier = Modifier.weight(0.6f))
    }
}

@Preview
@Composable
private fun DescriptionScreenAppBarPreview() {
    SurfSpringSchoolTheme {
        FavoriteScreenTopBar(
            modifier = Modifier,
            onBackClicked = {},
            onTitleClicked = {}
        )
    }
}
package dev.shevikina.surfspringschool.presentation.navigation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.shevikina.surfspringschool.R
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme

@Composable
fun NavItem(
    iconId: Int,
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val color =
        if (selected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondary

    Box(
        modifier = modifier.clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(18.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = iconId),
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = text,
                color = color,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(apiLevel = 34, showBackground = true)
@Composable
private fun NavItemPreview() {
    SurfSpringSchoolTheme {
        NavItem(
            iconId = R.drawable.search_icon,
            text = "Поиск",
            selected = true,
            modifier = Modifier.fillMaxWidth()
        ) {}
    }
}
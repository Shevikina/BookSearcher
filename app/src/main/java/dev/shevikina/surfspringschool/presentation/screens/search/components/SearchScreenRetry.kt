package dev.shevikina.surfspringschool.presentation.screens.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme

@Composable
fun SearchScreenRetry(
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Ошибка выполнения запроса,\nпопробуйте повторить",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextButton(
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.onSecondary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = onClick,
            modifier = Modifier.size(180.dp, 40.dp)
        ) {
            Text(
                text = "Попробовать еще",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview
@Composable
private fun SearchScreenRetryPreview() {
    SurfSpringSchoolTheme {
        SearchScreenRetry {}
    }
}
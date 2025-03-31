package dev.shevikina.surfspringschool.presentation.screens.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.shevikina.surfspringschool.R
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchTextField(
    searchQuery: String,
    modifier: Modifier = Modifier,
    sendQuery: (value: String) -> Unit,
    onValueChanged: (value: String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var timerJob by remember { mutableStateOf<Job?>(null) }

    LaunchedEffect(searchQuery) {
        if (searchQuery.isNotEmpty()) {
            timerJob = launch {
                delay(2000)
                sendQuery(searchQuery)
            }
        }
    }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = onValueChanged,
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        modifier = modifier.onFocusChanged { if (!it.hasFocus) timerJob?.cancel() },
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                timerJob?.cancel()
                sendQuery(searchQuery)
            }
        ),
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.search_icon),
                contentDescription = "Search icon",
                tint = MaterialTheme.colorScheme.secondary,
            )
        },
        placeholder = {
            Text(
                text = "Поиск",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedContainerColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        ),
        trailingIcon = {
            if (searchQuery.isNotEmpty())
                IconButton(onClick = { onValueChanged("") }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.close_icon),
                        contentDescription = "close",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
        }
    )
}

@Preview(apiLevel = 34)
@Composable
private fun SearchTextFieldPreview() {
    SurfSpringSchoolTheme {
        Box(Modifier.padding(vertical = 8.dp, horizontal = 20.dp)) {
            SearchTextField(
                searchQuery = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 50.dp),
                onValueChanged = {},
                sendQuery = {}
            )
        }
    }
}
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.shevikina.surfspringschool.R
import dev.shevikina.surfspringschool.ui.theme.SurfSpringSchoolTheme
import kotlinx.coroutines.delay

@Composable
fun SearchTextField(
    searchQuery: MutableState<String>,
    modifier: Modifier = Modifier,
    sendQuery: (value: String) -> Unit,
    onValueChanged: (value: String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    LaunchedEffect(searchQuery.value) {
        if (searchQuery.value.isNotEmpty()) {
            delay(2000)
            sendQuery(searchQuery.value)
        }
    }

    OutlinedTextField(
        value = searchQuery.value,
        onValueChange = {
            searchQuery.value = it
            onValueChanged(searchQuery.value)
        },
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        modifier = modifier,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                sendQuery(searchQuery.value)
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
            if (searchQuery.value.isNotEmpty())
                IconButton(onClick = {
                    searchQuery.value = ""
                    onValueChanged(searchQuery.value)
                }) {
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
                searchQuery = remember { mutableStateOf("") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 50.dp),
                onValueChanged = {},
                sendQuery = {}
            )
        }
    }
}
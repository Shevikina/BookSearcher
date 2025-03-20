package dev.shevikina.surfspringschool.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    tertiary = tertiaryColor,
    surface = surfaceColor,
    background = backgroundColor,
    onSecondary = onSecondaryColor,
    onBackground = onBackgroundColor
)

private val LightColorScheme = lightColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    tertiary = tertiaryColor,
    surface = surfaceColor,
    background = backgroundColor,
    onSecondary = onSecondaryColor,
    onBackground = onBackgroundColor
)

@Composable
fun SurfSpringSchoolTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
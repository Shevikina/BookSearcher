package dev.shevikina.surfspringschool.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import dev.shevikina.surfspringschool.R

val robotoFamily = FontFamily(
    Font(resId = R.font.roboto_regular, weight = FontWeight.Normal),
    Font(resId = R.font.roboto_medium, weight = FontWeight.Medium),
    Font(resId = R.font.roboto_bold, weight = FontWeight.Bold),
    Font(resId = R.font.roboto_light, weight = FontWeight.Light)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle( // Regular_16
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 1.em,
        letterSpacing = 0.em
    ),
    bodyLarge = TextStyle( // Regular_18
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 1.2.em,
        letterSpacing = 0.em
    ),
    bodySmall = TextStyle( // Regular_14
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 1.em,
        letterSpacing = 0.em
    ),
    labelMedium = TextStyle( // Bold_16
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 1.2.em,
        letterSpacing = 0.em
    ),
    labelLarge = TextStyle( // Bold_18
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 1.2.em,
        letterSpacing = 0.em
    ),
    labelSmall = TextStyle( // Medium_12
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.em
    )
)
package com.example.a30recipiesapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColorScheme(
    primary = Color(0xFFD7CCC8),
    secondary = Color(0xFFBCAAA4),
    tertiary = Color(0xFF8D6E63),
    background = Color(0xFF5D4037),
    surface = Color(0xFFD7CCC8),
    onPrimary = Color(0xFF3E2723),
    onSecondary = Color(0xFF3E2723),
    onBackground = Color(0xFFEFEBE9),
    onSurface = Color(0xFF3E2723)
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8D6E63),
    secondary = Color(0xFF5D4037),
    tertiary = Color(0xFFBCAAA4),
    background = Color(0xFF1E1E1E),
    surface = Color(0xFF2D2D2D),
    onPrimary = Color(0xFFEFEBE9),
    onSecondary = Color(0xFFEFEBE9),
    onBackground = Color(0xFFEFEBE9),
    onSurface = Color(0xFFEFEBE9)
)

@Composable
fun _30RecipiesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
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
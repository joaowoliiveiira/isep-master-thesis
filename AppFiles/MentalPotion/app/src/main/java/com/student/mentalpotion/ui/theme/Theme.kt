package com.student.mentalpotion.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val lightScheme = lightColorScheme(
    primary = White,
    onPrimary = Black,
    background = BackgroundDark,
    onBackground = White,
    surface = BackgroundDark,
    onSurface = White,
    secondary = GoldenBorder,
    onSecondary = Black,
    outline = GoldenBorder
)

private val darkScheme = darkColorScheme(
    primary = White,
    onPrimary = Black,
    background = BackgroundDark,
    onBackground = White,
    surface = BackgroundDark,
    onSurface = White,
    secondary = GoldenBorder,
    onSecondary = Black,
    outline = GoldenBorder
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = White,
    onPrimary = Black,
    background = BackgroundDark,
    onBackground = White,
    surface = BackgroundDark,
    onSurface = White,
    secondary = GoldenBorder,
    onSecondary = Black,
    outline = GoldenBorder
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = White,
    onPrimary = Black,
    background = BackgroundDark,
    onBackground = White,
    surface = BackgroundDark,
    onSurface = White,
    secondary = GoldenBorder,
    onSecondary = Black,
    outline = GoldenBorder
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = White,
    onPrimary = Black,
    background = BackgroundDark,
    onBackground = White,
    surface = BackgroundDark,
    onSurface = White,
    secondary = GoldenBorder,
    onSecondary = Black,
    outline = GoldenBorder
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = White,
    onPrimary = Black,
    background = BackgroundDark,
    onBackground = White,
    surface = BackgroundDark,
    onSurface = White,
    secondary = GoldenBorder,
    onSecondary = Black,
    outline = GoldenBorder
)

@Composable
fun MentalPotionTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> highContrastDarkColorScheme
        else -> highContrastLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
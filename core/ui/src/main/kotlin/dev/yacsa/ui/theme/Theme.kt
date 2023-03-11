package dev.yacsa.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun YacsaTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    val colors = if (useDarkTheme) {
        baseDarkColorPalette
    } else {
        baseLightColorPalette
    }

    with(systemUiController) {
        setSystemBarsColor(
            color = colors.statusBarColor
        )
        setNavigationBarColor(
            color = colors.statusBarColor
        )
    }

    CompositionLocalProvider(
        LocalYacsaColors provides colors,
        LocalYacsaTypography provides typography,
        content = content
    )
}
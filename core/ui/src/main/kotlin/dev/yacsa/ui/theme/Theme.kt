package dev.yacsa.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun YacsaTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    val colors = if (useDarkTheme) {
        baseDarkColorPalette
    } else {
        baseLightColorPalette
    }

    with(systemUiController) {
        setSystemBarsColor(
            color = colors.statusBar,
        )
        setNavigationBarColor(
            color = colors.navigationBar,
        )
    }

    CompositionLocalProvider(
        LocalYacsaColors provides colors,
        LocalYacsaTypography provides typography,
        LocalYacsaSpacing provides spacing,
        LocalYacsaShape provides shapes,
        LocalYacsaDividers provides dividers,
        LocalYacsaCorners provides corners,
        content = content,

    )
}

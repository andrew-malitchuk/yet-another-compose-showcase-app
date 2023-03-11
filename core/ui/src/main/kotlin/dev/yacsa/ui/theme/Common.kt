package dev.yacsa.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class YacsaColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
    val statusBarColor:Color
)

data class YacsaTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val caption: TextStyle
)

data class YacsaShape(
    val padding: Dp,
    val cornersStyle: Shape
)

object YacsaTheme {
    val colors: YacsaColors
        @Composable
        get() = LocalYacsaColors.current
    val typography: YacsaTypography
        @Composable
        get() = LocalYacsaTypography.current

    val shapes: YacsaShape
        @Composable
        get() = LocalYacsaShape.current
}

internal val LocalYacsaColors = staticCompositionLocalOf<YacsaColors> {
    error("No colors provided")
}

internal val LocalYacsaTypography = staticCompositionLocalOf<YacsaTypography> {
    error("No font provided")
}

internal val LocalYacsaShape = staticCompositionLocalOf<YacsaShape> {
    error("No shapes provided")
}
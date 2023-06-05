package dev.yacsa.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class YacsaColors(
    val background:Color,
    val surface:Color,
    val accent:Color,
    val primary:Color,
    val secondary:Color,

    val statusBar:Color,
    val navigationBar:Color,
)

data class YacsaTypography(
    val header: TextStyle,
    val caption: TextStyle,
    val title: TextStyle,
    val description: TextStyle,
    val body: TextStyle,
)

data class YacsaShape(
    val padding: Dp,
    val cornersStyle: Shape,
)

data class YacsaSpacing(
    val extraSmall: Dp,
    val small: Dp,
    val medium: Dp,
    val default: Dp,
    val large: Dp,
    val extraLarge: Dp,
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

    val spacing: YacsaSpacing
        @Composable
        get() = LocalYacsaSpacing.current
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

internal val LocalYacsaSpacing = staticCompositionLocalOf<YacsaSpacing> {
    error("No shapes provided")
}

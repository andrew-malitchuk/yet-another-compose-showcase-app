package dev.yacsa.ui.theme

import androidx.compose.ui.graphics.Color

//region Light colors
val yacsa_theme_light_background = Color(0xFFFFFFFF)
val yacsa_theme_light_surface = Color(0xFFF7EFE7)
val yacsa_theme_light_accent = Color(0xFFF77A36)
val yacsa_theme_light_primary = Color(0xFF62516D)
val yacsa_theme_light_secondary = Color(0xFF222222)
val yacsa_theme_light_bar_status = Color(0xFFFFFFFF)
val yacsa_theme_light_bar_navigation = Color(0xFFCCCCCC)
//endregion Light colors

//region Dark colors
val yacsa_theme_dark_background = Color(0xFF000000)
val yacsa_theme_dark_surface = Color(0xFF222022)
val yacsa_theme_dark_accent = Color(0xFFF77A36)
val yacsa_theme_dark_primary = Color(0xFFFFFFFF)
val yacsa_theme_dark_secondary = Color(0xFFCECECE)
val yacsa_theme_dark_bar_status = Color(0xFF000000)
val yacsa_theme_dark_bar_navigation = Color(0xFF8F9491)
//endregion Dark colors

val baseLightColorPalette = YacsaColors(
    background = yacsa_theme_light_background,
    surface = yacsa_theme_light_surface,
    accent = yacsa_theme_light_accent,
    primary = yacsa_theme_light_primary,
    secondary = yacsa_theme_light_secondary,
    statusBar = yacsa_theme_light_bar_status,
    navigationBar = yacsa_theme_light_bar_navigation,
)

val baseDarkColorPalette = YacsaColors(
    background = yacsa_theme_dark_background,
    surface = yacsa_theme_dark_surface,
    accent = yacsa_theme_dark_accent,
    primary = yacsa_theme_dark_primary,
    secondary = yacsa_theme_dark_secondary,
    statusBar = yacsa_theme_dark_bar_status,
    navigationBar = yacsa_theme_dark_bar_navigation,
)

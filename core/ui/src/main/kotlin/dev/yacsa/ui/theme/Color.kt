package dev.yacsa.ui.theme

/**
 * Primary - most frequently displayed color across the app.
 * Secondary - accent color.
 *
 * Primary variant -
 * Secondary variant -
 *
 * Surface -
 * Background -
 * Error -
 *
 * On Primary -
 * On Secondary -
 * On Background -
 * On Surface -
 * On Error -
 */

import androidx.compose.ui.graphics.Color

//region Light colors
val yacsa_theme_light_primary = Color(0xFF6750A4)
val yacsa_theme_light_onPrimary = Color(0xFFFFFFFF)
val yacsa_theme_light_secondary = Color(0xFF625B71)
val yacsa_theme_light_onSecondary = Color(0xFFFFFFFF)
val yacsa_theme_light_error = Color(0xFFBA1A1A)
val yacsa_theme_light_onError = Color(0xFFFFFFFF)
val yacsa_theme_light_background = Color(0xFFFFFBFF)
val yacsa_theme_light_onBackground = Color(0xFF1C1B1E)
val yacsa_theme_light_surface = Color(0xFFFFFBFF)
val yacsa_theme_light_onSurface = Color(0xFF1C1B1E)
//endregion Light colors

//region Dark colors
val yacsa_theme_dark_primary = Color(0xFFCFBCFF)
val yacsa_theme_dark_onPrimary = Color(0xFF381E72)
val yacsa_theme_dark_secondary = Color(0xFFCBC2DB)
val yacsa_theme_dark_onSecondary = Color(0xFF332D41)
val yacsa_theme_dark_error = Color(0xFFFFB4AB)
val yacsa_theme_dark_onError = Color(0xFF690005)
val yacsa_theme_dark_background = Color(0xFF1C1B1E)
val yacsa_theme_dark_onBackground = Color(0xFFE6E1E6)
val yacsa_theme_dark_surface = Color(0xFF1C1B1E)
val yacsa_theme_dark_onSurface = Color(0xFFE6E1E6)
//endregion Dark colors

val baseLightColorPalette = YacsaColors(
    primaryText = yacsa_theme_light_primary,
    primaryBackground = yacsa_theme_light_background,
    secondaryText = yacsa_theme_light_secondary,
    secondaryBackground = yacsa_theme_light_onSecondary,
    tintColor = yacsa_theme_light_onSurface,
    statusBarColor = yacsa_theme_light_background
)

val baseDarkColorPalette = YacsaColors(
    primaryText = yacsa_theme_dark_primary,
    primaryBackground = yacsa_theme_dark_background,
    secondaryText = yacsa_theme_dark_secondary,
    secondaryBackground = yacsa_theme_dark_onSecondary,
    tintColor = yacsa_theme_dark_onSurface,
    statusBarColor = yacsa_theme_dark_background
)
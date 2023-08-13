package dev.yacsa.ui.composable.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import dev.yacsa.model.model.theme.ThemeUiModel
import dev.yacsa.model.model.theme.isAuto
import dev.yacsa.model.model.theme.isDark

@Composable
fun ThemeUiModel.detectThemeMode(): Boolean {
    return if (isAuto()) {
        isSystemInDarkTheme()
    } else {
        isDark()
    }
}

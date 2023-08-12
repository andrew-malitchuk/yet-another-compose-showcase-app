package dev.yacsa.model.model.theme

enum class ThemeUiModel {
    LIGHT, DARK, AUTO
}

fun ThemeUiModel.isLight(): Boolean {
    return this == ThemeUiModel.LIGHT
}

fun ThemeUiModel.isDark(): Boolean {
    return this == ThemeUiModel.DARK
}

fun ThemeUiModel.isAuto(): Boolean {
    return this == ThemeUiModel.AUTO
}

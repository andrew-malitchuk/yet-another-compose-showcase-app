package dev.yacsa.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val typography = YacsaTypography(
    heading = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),
    body = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    caption = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
)
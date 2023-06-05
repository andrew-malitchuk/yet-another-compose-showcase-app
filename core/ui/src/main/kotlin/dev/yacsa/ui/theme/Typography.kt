package dev.yacsa.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val typography = YacsaTypography(
    header = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    ),
    caption = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
    ),
    description = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    title = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    ),
    body = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
)

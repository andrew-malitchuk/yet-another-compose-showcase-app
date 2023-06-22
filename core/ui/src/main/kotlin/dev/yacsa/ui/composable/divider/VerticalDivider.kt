package dev.yacsa.ui.composable.divider

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun VerticalDivider() {
    Divider(
        modifier = Modifier
            .fillMaxHeight()
            .width(YacsaTheme.dividers.large),
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_VerticalDivider_Light() {
    YacsaTheme(false) {
        VerticalDivider()
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_VerticalDivider_Dark() {
    YacsaTheme(true) {
        VerticalDivider()
    }
}

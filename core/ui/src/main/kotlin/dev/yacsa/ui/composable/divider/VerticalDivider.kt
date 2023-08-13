package dev.yacsa.ui.composable.divider

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.ui.theme.YacsaTheme
import io.github.serpro69.kfaker.Faker

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
    val faker = Faker()
    YacsaTheme(false) {
        VerticalDivider()
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_VerticalDivider_Dark() {
    val faker = Faker()
    YacsaTheme(true) {
        VerticalDivider()
    }
}

package dev.yacsa.settings.screen.settings.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.settings.screen.settings.composable.ThemeButton
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.modifier.bouncingClickable
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ThemeItem(
    modifier: Modifier = Modifier,
) {

    val shape = RoundedCornerShape(16.dp)
    Card(
        shape = shape,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .bouncingClickable {

            },
        border = BorderStroke(1.dp, Color(0xFF7766C6)),
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SmallFloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_paint_roller_bold_24),
                    contentDescription = null
                )
            }
            Spacer(
                modifier = Modifier
                    .width(16.dp)
            )
            Text(text = "Theme", style = YacsaTheme.typography.title)
            Spacer(
                modifier = Modifier
                    .width(8.dp)
            )
            LazyRow() {
                item {
                    ThemeButton(theme = YacsaTheme, currentTheme = YacsaTheme) {

                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview_ThemeItem() {
    YacsaTheme {
        ThemeItem()
    }
}
package dev.yacsa.books.screen.list.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme


@Composable
fun ItemLanguage(
    lang: String,
    onClick: () -> Unit,
) {
    val shape = YacsaTheme.shapes.cornersStyle
    Card(
        shape = shape,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
            ) {
                onClick()
            },
        backgroundColor = YacsaTheme.colors.surface,
        /*border = BorderStroke(0.5.dp, YacsaTheme.colors.primary),*/
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SmallFloatingActionButton(
                onClick = { },
                containerColor = YacsaTheme.colors.primary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_translate_regular_24),
                    contentDescription = null,
                    tint = YacsaTheme.colors.accent
                )
            }
            Spacer(
                modifier = Modifier
                    .width(16.dp),
            )

            Text(
                text = lang,
                style = YacsaTheme.typography.title,
                color = YacsaTheme.colors.secondary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ItemLanguage() {
    YacsaTheme {
        ItemLanguage(
            lang = "ua",
            onClick = {},
        )
    }
}
package dev.yacsa.deeplink.screen.deeplink.item

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.FloatingActionButtonDefaults
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DeeplinkItem(
    modifier: Modifier = Modifier,
    value: String,
    @DrawableRes
    icon: Int,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(YacsaTheme.corners.medium)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
            ) {
                onClick()
            },
        contentColor = YacsaTheme.colors.background,
        backgroundColor = YacsaTheme.colors.background,
        shape = YacsaTheme.shapes.cornersStyle,
        elevation = 0.dp,

        ) {
        Row(
            modifier = Modifier
                .padding(YacsaTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SmallFloatingActionButton(
                onClick = { },
                containerColor = YacsaTheme.colors.primary,
                elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp)
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = YacsaTheme.colors.accent
                )
            }
            Spacer(
                modifier = Modifier
                    .width(YacsaTheme.spacing.medium),
            )
            Column {
                Text(
                    text = value, modifier = Modifier.basicMarquee(),
                    style = YacsaTheme.typography.caption, color = YacsaTheme.colors.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_SettingsItem() {
    YacsaTheme {
        DeeplinkItem(
            value = "foobar",
            icon = R.drawable.icon_bell_regular_24,
            onClick = {},
        )
    }
}
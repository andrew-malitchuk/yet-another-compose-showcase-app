package dev.yacsa.books.screen.list.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import dev.yacsa.model.model.PersonUiModel
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme


@Composable
fun ItemAuthor(
    person: PersonUiModel,
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
                ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_user_circle_regular_24),
                    contentDescription = null,
                    tint = YacsaTheme.colors.accent
                )
            }
            Spacer(
                modifier = Modifier
                    .width(YacsaTheme.spacing.medium),
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = person.name ?: "SWW",
                    style = YacsaTheme.typography.title,
                    color = YacsaTheme.colors.secondary
                )
                Text(
                    text = "${person.birthYear}-${person.deathYear}",
                    style = YacsaTheme.typography.description,
                    color = YacsaTheme.colors.secondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_SettingsItem() {
    YacsaTheme {
        ItemAuthor(
            person = PersonUiModel(
                1,
                1000,
                2000,
                "Foobar"
            ),
            onClick = {},
        )
    }
}
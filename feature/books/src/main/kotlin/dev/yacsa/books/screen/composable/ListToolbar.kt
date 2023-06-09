package dev.yacsa.books.screen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.divider.AnimatedDivider
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ListToolbar(
    modifier: Modifier = Modifier,
    state: LazyListState,
    searchClick: () -> Unit,
    settingsClick: () -> Unit,
    favouriteClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(YacsaTheme.colors.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = YacsaTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_book_regular_24),
                contentDescription = null,
                tint = YacsaTheme.colors.accent

            )
            Spacer(
                modifier = Modifier.width(YacsaTheme.spacing.small),
            )
            Text(
                text = "YACSA",
                style = YacsaTheme.typography.title,
                color = YacsaTheme.colors.primary
            )

            Spacer(
                modifier = Modifier
                    .weight(1f),
            )
            SmallFloatingActionButton(
                containerColor = YacsaTheme.colors.primary,
                onClick = { favouriteClick() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_heart_regulat_24),
                    contentDescription = null,
                    tint = YacsaTheme.colors.accent
                )
            }
            SmallFloatingActionButton(
                containerColor = YacsaTheme.colors.primary,
                onClick = { searchClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_search_regular_24),
                    contentDescription = null,
                    tint = YacsaTheme.colors.accent

                )
            }
            SmallFloatingActionButton(
                containerColor = YacsaTheme.colors.primary,
                onClick = { settingsClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_gear_six_regular_24),
                    contentDescription = null,
                    tint = YacsaTheme.colors.accent
                )
            }
        }
        AnimatedDivider(state = state)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ListToolbar() {
    YacsaTheme {
        ListToolbar(
            state = rememberLazyListState(),
            searchClick = {},
            settingsClick = {},
            favouriteClick = {}
        )
    }
}

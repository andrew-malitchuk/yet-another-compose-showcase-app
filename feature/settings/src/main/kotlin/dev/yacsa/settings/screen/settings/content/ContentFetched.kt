package dev.yacsa.settings.screen.settings.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.yacsa.model.model.theme.ThemeUiModel
import dev.yacsa.settings.screen.settings.item.SettingsItem
import dev.yacsa.settings.screen.settings.item.ThemeItem
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme
import java.lang.Math.abs

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSnapperApi::class)
@Composable
fun ContentFetched(
    modifier: Modifier = Modifier.fillMaxSize(),
    innerPadding: PaddingValues,
    state: LazyListState,
    foo: TopAppBarState,
    onFfClick: () -> Unit,
    onAnalyticsClick: () -> Unit,
    onDeeplinkClick: () -> Unit,
    theme: MutableState<ThemeUiModel?>,
) {
    val corner = YacsaTheme.corners.medium - (YacsaTheme.corners.medium * abs(foo.collapsedFraction))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(YacsaTheme.colors.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = corner, topEnd = corner))
                .background(YacsaTheme.colors.background),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                state = state,
                contentPadding = PaddingValues(YacsaTheme.spacing.small),
                verticalArrangement = Arrangement.spacedBy(YacsaTheme.spacing.small),
            ) {
                item {
                    SettingsItem(
                        title = "Feature flag",
                        icon = R.drawable.icon_command_regular_24,
                        onClick = {
                            onFfClick()
                        },
                    )
                }
                item {
                    SettingsItem(
                        title = "Information",
                        icon = R.drawable.icon_info_regular_24,
                        onClick = {
                        },
                    )
                }
                item {
                    SettingsItem(
                        title = "Analytics",
                        icon = R.drawable.icon_flask_regular_24,
                        onClick = {
                            onAnalyticsClick()
                        },
                    )
                }
                item {
                    SettingsItem(
                        title = "Deeplink",
                        icon = R.drawable.icon_link_regular_24,
                        onClick = {
                            onDeeplinkClick()
                        },
                    )
                }
                item {
                    ThemeItem(
                        Modifier,
                        theme
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Preview_ContentFetched() {
    YacsaTheme {
        ContentFetched(
            innerPadding = PaddingValues(YacsaTheme.spacing.small),
            state = rememberLazyListState(),
            foo = rememberTopAppBarState(),
            onFfClick = {},
            onAnalyticsClick = {},
            onDeeplinkClick = {},
            theme = remember { mutableStateOf(null) },
        )
    }
}

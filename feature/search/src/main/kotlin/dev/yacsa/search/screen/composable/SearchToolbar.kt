package dev.yacsa.search.screen.composable

import android.annotation.SuppressLint
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
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Text
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.button.TwoStateButton
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun SearchToolbar(
    modifier: Modifier = Modifier,
    state: LazyListState,
    onBackClick: () -> Unit,
    onFilterClick: () -> Unit,
    filterState: MutableState<Boolean>,
    showSheet: MutableState<Boolean?>
) {
    Column(
        modifier = Modifier.background(YacsaTheme.colors.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = YacsaTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SmallFloatingActionButton(
                onClick = { onBackClick() },
                containerColor = YacsaTheme.colors.accent,
                elevation = FloatingActionButtonDefaults.elevation(0.dp,0.dp,0.dp,0.dp)
            ) {
                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.icon_caret_left_regular_24),
                    contentDescription = null,
                    tint = YacsaTheme.colors.primary
                )
            }
            Text(
                text = UiText.StringResource(dev.yacsa.localization.R.string.search_search).asString(),
                style = YacsaTheme.typography.header,
                color = YacsaTheme.colors.primary
            )
            Spacer(modifier = Modifier.width(YacsaTheme.spacing.small))
            Icon(
                painterResource(id = R.drawable.icon_search_bold_24),
                contentDescription = null,
                tint = YacsaTheme.colors.accent
            )
            Spacer(
                modifier = Modifier
                    .weight(1f),
            )
            BadgedBox(
                modifier = Modifier.padding(
                    start = YacsaTheme.spacing.extraSmall,
                    top = YacsaTheme.spacing.small,
                    bottom = YacsaTheme.spacing.small
                ),
                badge = {
                    if (filterState.value) {
                        Badge(contentColor = YacsaTheme.colors.accent)
                    }
                }
            ) {
                TwoStateButton(
                    modifier = Modifier,
                    checkedState = showSheet,
                    defaultIcon = R.drawable.icon_filter_regulat_24,
                    selectedIcon = R.drawable.icon_filter_bold_24,
                    onButtonClick = {
                        showSheet.value = it
                    }
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun Preview_SearchToolbar_Light() {
    YacsaTheme(false) {
        SearchToolbar(
            state = rememberLazyListState(),
            onBackClick = {},
            onFilterClick = {},
            filterState = mutableStateOf(false),
            showSheet = mutableStateOf(false),
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun Preview_SearchToolbar_Dark() {
    YacsaTheme(true) {
        SearchToolbar(
            state = rememberLazyListState(),
            onBackClick = {},
            onFilterClick = {},
            filterState = mutableStateOf(false),
            showSheet = mutableStateOf(false),
        )
    }
}

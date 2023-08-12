package dev.yacsa.search.screen.search.content.result

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.search.screen.search.item.ItemFetchedList
import dev.yacsa.ui.composable.divider.AnimatedDivider
import dev.yacsa.ui.theme.YacsaTheme
import io.github.serpro69.kfaker.Faker

@Composable
fun ResultFetched(
    modifier: Modifier = Modifier,
    resultSearch: List<BookUiModel>,
    onBookClicked: (Int) -> Unit,
    state: LazyListState,
) {
    val isVisible = state.canScrollBackward
    val corner = animateDpAsState(
        targetValue = if (!isVisible) YacsaTheme.corners.medium else 0.dp,
        animationSpec = tween(
            durationMillis = 500,
        ),
    )

    Column(
        modifier = Modifier
            .padding(top = YacsaTheme.spacing.small),
    ) {
        AnimatedDivider(state = state)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = corner.value, topEnd = corner.value))
                .background(YacsaTheme.colors.surface),
        ) {
            LazyColumn(
                state = state,
                modifier = modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(YacsaTheme.spacing.small),
                verticalArrangement = Arrangement.spacedBy(YacsaTheme.spacing.small),
            ) {
                items(items = resultSearch) { item ->
                    ItemFetchedList(
                        book = item,
                        onItemContentClick = {
                            // TODO: fix
                            item.id?.let { onBookClicked(it) }
                        },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ResultFetched_Light() {
    val faker = Faker()
    YacsaTheme(false) {
        ResultFetched(
            resultSearch = emptyList(),
            onBookClicked = {},
            state = rememberLazyListState(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ResultFetched_Dark() {
    val faker = Faker()
    YacsaTheme(false) {
        ResultFetched(
            resultSearch = emptyList(),
            onBookClicked = {},
            state = rememberLazyListState(),
        )
    }
}

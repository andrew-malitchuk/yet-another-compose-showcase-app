package dev.yacsa.books.screen.list.content.fetched

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import dev.yacsa.books.screen.list.item.ItemError
import dev.yacsa.books.screen.list.item.ItemFetchedList
import dev.yacsa.books.screen.list.item.ItemLoading
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.theme.YacsaTheme
import io.github.serpro69.kfaker.Faker
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentFetchedList(
    modifier: Modifier = Modifier,
    lazyPagingItems: LazyPagingItems<BookUiModel>,
    onBookClicked: (Int) -> Unit,
    listState: LazyListState,
) {
    val isVisible = listState.canScrollBackward

    val corner = animateDpAsState(
        targetValue = if (!isVisible) YacsaTheme.corners.medium else 0.dp,
        animationSpec = tween(
            durationMillis = 500,
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = corner.value, topEnd = corner.value))
            .background(YacsaTheme.colors.surface),
    ) {
        LazyColumn(
            state = listState,
            modifier = modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(YacsaTheme.spacing.small),
            verticalArrangement = Arrangement.spacedBy(YacsaTheme.spacing.small),
        ) {
            items(
                count = lazyPagingItems.itemCount,
                key=lazyPagingItems.itemKey { it.id!! }
            ) { index ->
                val item = lazyPagingItems[index]
                item?.let {
                    ItemFetchedList(
                        modifier=Modifier.animateItemPlacement(),
                        book = item,
                        onItemContentClick = {
                            // TODO: fix
                            it.id?.let { it1 -> onBookClicked(it1) }
                        },
                    )
                }
            }
            lazyPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            ItemLoading()
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = lazyPagingItems.loadState.append as? LoadState.Error
                        item {
                            ItemError(
                                error = error?.error?.localizedMessage ?: UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).asString(),
                                onRetry = {
                                    retry()
                                },
                            )
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            ItemLoading()
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = lazyPagingItems.loadState.append as LoadState.Error
                        item {
                            ItemError(
                                error = error.error.localizedMessage ?: UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).asString(),
                                onRetry = {
                                    retry()
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentFetchedList_Light() {
    val faker = Faker()
    YacsaTheme(false) {
        ContentFetchedList(
            lazyPagingItems = flowOf(PagingData.empty<BookUiModel>()).collectAsLazyPagingItems(),
            onBookClicked = {},
            listState = rememberLazyListState(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentFetchedList_Dark() {
    val faker = Faker()
    YacsaTheme(true) {
        ContentFetchedList(
            lazyPagingItems = flowOf(PagingData.empty<BookUiModel>()).collectAsLazyPagingItems(),
            onBookClicked = {},
            listState = rememberLazyListState(),
        )
    }
}

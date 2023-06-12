package dev.yacsa.books.screen.list.content.fetched

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import dev.yacsa.books.screen.list.item.ItemError
import dev.yacsa.books.screen.list.item.ItemFetchedGrid
import dev.yacsa.books.screen.list.item.ItemLoading
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.theme.YacsaTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun ContentFetchedGrid(
    modifier: Modifier = Modifier,
    spanCount: Int = 2,
    lazyPagingItems: LazyPagingItems<BookUiModel>,
    onBookClicked: (Int) -> Unit,
    listState: LazyGridState,
) {
    val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(spanCount) }

    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(YacsaTheme.spacing.small),
        horizontalArrangement = Arrangement.spacedBy(YacsaTheme.spacing.small),
        state = listState,
        columns = GridCells.Fixed(spanCount),
        modifier = modifier
            .fillMaxSize()
            // TODO: fix
            .padding(YacsaTheme.spacing.medium),
    ) {
        items(
            lazyPagingItems.itemCount,
        ) { index ->
            lazyPagingItems[index]?.let { item ->

                ItemFetchedGrid(
                    book = item,
                    onItemContentClick = {
                        item.id?.let { onBookClicked(it) }
                    },
                )
            }
        }
        lazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item(span = span) {
                        ItemLoading()
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val error = lazyPagingItems.loadState.append as? LoadState.Error
                    item {
                        ItemError(
                            error = error?.error?.localizedMessage ?: "SWW",
                            onRetry = {
                                retry()
                            },
                        )
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item(span = span) {
                        ItemLoading()
                    }
                }
                loadState.append is LoadState.Error -> {
                    val error = lazyPagingItems.loadState.append as LoadState.Error
                    item {
                        ItemError(
                            error = error.error.localizedMessage ?: "SWW",
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

@Preview(showBackground = true)
@Composable
fun Preview_ContentFetchedGrid_Light() {
    YacsaTheme(false) {
        ContentFetchedGrid(
            spanCount = 2,
            lazyPagingItems = flowOf(PagingData.empty<BookUiModel>()).collectAsLazyPagingItems(),
            onBookClicked = {},
            listState = rememberLazyGridState(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentFetchedGrid_Dark() {
    YacsaTheme(true) {
        ContentFetchedGrid(
            spanCount = 2,
            lazyPagingItems = flowOf(PagingData.empty<BookUiModel>()).collectAsLazyPagingItems(),
            onBookClicked = {},
            listState = rememberLazyGridState(),
        )
    }
}

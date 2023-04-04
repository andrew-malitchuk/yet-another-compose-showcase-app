package dev.yacsa.books.screen.list.content.fetched

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    listState: LazyGridState
) {
    val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(spanCount) }

    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Fixed(spanCount),
        modifier = modifier
            .fillMaxSize()
            // TODO: fix
            .padding(
                horizontal = 16.dp
            ),
    ) {
        items(
            lazyPagingItems.itemCount
        ) { index ->
            lazyPagingItems[index]?.let { item ->

                ItemFetchedGrid(
                    title = item?.title ?: "",
                    description = item?.authors?.firstOrNull()?.name ?: "NI",
                    imageUrl = item?.formats?.imageJpeg,
                    onItemContentClick = {
                        item?.id?.let { onBookClicked(it) }
                    }
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
                            }
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
                            }
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview_ContentFetchedGrid() {
    YacsaTheme() {
        ContentFetchedGrid(
            spanCount = 2,
            lazyPagingItems = flowOf(PagingData.empty<BookUiModel>()).collectAsLazyPagingItems(),
            onBookClicked = {},
            listState = rememberLazyGridState()
        )
    }
}
package dev.yacsa.books.screen.list.content.fetched

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.yacsa.books.screen.list.item.ItemError
import dev.yacsa.books.screen.list.item.ItemFetchedList
import dev.yacsa.books.screen.list.item.ItemLoading
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.theme.YacsaTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun ContentFetchedList(
    modifier: Modifier = Modifier,
    lazyPagingItems: LazyPagingItems<BookUiModel>,
    onBookClicked: (Int) -> Unit,
    listState: LazyListState,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = listState,
        modifier = modifier
            .fillMaxSize()
            // TODO: fix
            .padding(
                horizontal = 16.dp,
            ),
    ) {
        items(
            lazyPagingItems,
        ) { item ->
            item?.let {
                ItemFetchedList(
                    book = it,
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
                            error = error?.error?.localizedMessage ?: "SWW",
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
fun Preview_ContentFetchedList() {
    YacsaTheme() {
        ContentFetchedList(
            lazyPagingItems = flowOf(PagingData.empty<BookUiModel>()).collectAsLazyPagingItems(),
            onBookClicked = {},
            listState = rememberLazyListState(),
        )
    }
}

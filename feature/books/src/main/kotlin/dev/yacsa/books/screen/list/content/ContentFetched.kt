package dev.yacsa.books.screen.list.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.yacsa.books.screen.list.item.ItemError
import dev.yacsa.books.screen.list.item.ItemFetched
import dev.yacsa.books.screen.list.item.ItemLoading
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.composable.fab.ScrollUpFab
import dev.yacsa.ui.theme.YacsaTheme
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ContentFetched(
    modifier: Modifier = Modifier,
    onBookClicked: (Int) -> Unit,
    lazyPagingItems: LazyPagingItems<BookUiModel>
) {
    val state = rememberLazyListState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = lazyPagingItems.loadState.refresh is LoadState.Loading,
        onRefresh = {
            lazyPagingItems.refresh()
        }
    )

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text("I'm a TopAppBar")
            },
            backgroundColor = YacsaTheme.colors.primaryText,
            actions = {
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Outlined.Search, null)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Outlined.Settings, null)
                }
            }
        )

        Box(Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(
                state = listState,
                modifier = modifier
                    .fillMaxSize()
                    // TODO: fix
                    .padding(
                        horizontal = 16.dp
                    ),
                flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
            ) {
                items(
                    lazyPagingItems
                ) { item ->
                    ItemFetched(
                        title = item?.title ?: "",
                        description = item?.authors?.firstOrNull()?.name ?: "NI",
                        imageUrl = item?.formats?.imageJpeg,
                        onItemContentClick = {
                            item?.id?.let { onBookClicked(it) }
                        }
                    )
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
                                    }
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
                                    }
                                )
                            }
                        }
                    }
                }
            }
            ScrollUpFab(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    // TODO: fix
                    .padding(16.dp),
                isVisibleBecauseOfScrolling = !listState.isScrollInProgress && listState.canScrollBackward
            ) {
                coroutineScope.launch {
                    listState.animateScrollToItem(0)
                }
            }

            PullRefreshIndicator(
                lazyPagingItems.loadState.refresh is LoadState.Loading,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentFetched() {
    YacsaTheme {
        ContentFetched(
            onBookClicked = {},
            lazyPagingItems = flowOf(PagingData.empty<BookUiModel>()).collectAsLazyPagingItems()
        )
    }
}
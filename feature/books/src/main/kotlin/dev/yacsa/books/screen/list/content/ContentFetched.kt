package dev.yacsa.books.screen.list.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import dev.yacsa.books.screen.composable.ListToolbar
import dev.yacsa.books.screen.list.content.fetched.ContentFetchedGrid
import dev.yacsa.books.screen.list.content.fetched.ContentFetchedList
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
    lazyPagingItems: LazyPagingItems<BookUiModel>,
    onSearch: () -> Unit,
    onSettings: () -> Unit,
) {
    val listState = rememberLazyListState()
    val gridState = rememberLazyGridState()

    val isGridSelected = remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = lazyPagingItems.loadState.refresh is LoadState.Loading,
        onRefresh = {
            lazyPagingItems.refresh()
        },
    )

    val coroutineScope = rememberCoroutineScope()

    Column {
        ListToolbar(
            state =listState,
            searchClick = onSearch,
            settingsClick = onSettings
        )

        Box(Modifier.pullRefresh(pullRefreshState)) {
            if (isGridSelected.value) {
                ContentFetchedGrid(
                    lazyPagingItems = lazyPagingItems,
                    onBookClicked = onBookClicked,
                    listState = gridState,
                )
            } else {
                ContentFetchedList(
                    lazyPagingItems = lazyPagingItems,
                    onBookClicked = onBookClicked,
                    listState = listState,
                )
            }


            ScrollUpFab(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    // TODO: fix
                    .padding(16.dp),
                isVisibleBecauseOfScrolling =
                if (isGridSelected.value) {
                    !gridState.isScrollInProgress && gridState.canScrollBackward
                } else {
                    !listState.isScrollInProgress && listState.canScrollBackward
                },
            ) {
                coroutineScope.launch {
                    if (isGridSelected.value) {
                        gridState.animateScrollToItem(0)
                    } else {
                        listState.animateScrollToItem(0)
                    }
                }
            }

            PullRefreshIndicator(
                lazyPagingItems.loadState.refresh is LoadState.Loading,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter),
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
            lazyPagingItems = flowOf(PagingData.empty<BookUiModel>()).collectAsLazyPagingItems(),
            onSearch = {},
            onSettings = {},
        )
    }
}

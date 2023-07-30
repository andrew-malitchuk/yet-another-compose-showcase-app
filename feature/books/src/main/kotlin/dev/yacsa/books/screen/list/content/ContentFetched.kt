package dev.yacsa.books.screen.list.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yacsa.books.screen.composable.ListToolbar
import dev.yacsa.books.screen.list.content.fetched.ContentFetchedGrid
import dev.yacsa.books.screen.list.content.fetched.ContentFetchedList
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.composable.fab.ScrollUpFab
import dev.yacsa.ui.theme.YacsaTheme
import io.github.serpro69.kfaker.Faker
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ContentFetched(
    onBookClicked: (Int) -> Unit,
    lazyPagingItems: LazyPagingItems<BookUiModel>,
    onSearch: () -> Unit,
    onSettings: () -> Unit,
    onFavourite: () -> Unit,
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
    val systemUiController = rememberSystemUiController()

    with(systemUiController) {
        setSystemBarsColor(
            color = dev.yacsa.ui.theme.YacsaTheme.colors.background,
        )
        setNavigationBarColor(
            color = dev.yacsa.ui.theme.YacsaTheme.colors.surface,
        )
    }

    Column {
        ListToolbar(
            state = listState,
            searchClick = onSearch,
            settingsClick = onSettings,
            favouriteClick = onFavourite,
        )

        Box(
            modifier = Modifier
                .background(YacsaTheme.colors.background)
                .pullRefresh(pullRefreshState),

        ) {
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
                    .padding(YacsaTheme.spacing.medium),
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
fun Preview_ContentFetched_Light() {
    val faker = Faker()
    YacsaTheme(false) {
        ContentFetched(
            onBookClicked = {},
            lazyPagingItems = flowOf(PagingData.empty<BookUiModel>()).collectAsLazyPagingItems(),
            onSearch = {},
            onSettings = {},
            onFavourite = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentFetched_Dark() {
    val faker = Faker()
    YacsaTheme(true) {
        ContentFetched(
            onBookClicked = {},
            lazyPagingItems = flowOf(PagingData.empty<BookUiModel>()).collectAsLazyPagingItems(),
            onSearch = {},
            onSettings = {},
            onFavourite = {},
        )
    }
}

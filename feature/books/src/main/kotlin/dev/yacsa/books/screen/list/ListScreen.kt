package dev.yacsa.books.screen.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.material.pullrefresh.PullRefreshDefaults
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yacsa.books.screen.list.list.ListItem
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.platform.ext.collectWithLifecycle
import dev.yacsa.ui.theme.YacsaTheme
import kotlinx.coroutines.flow.Flow
import logcat.logcat

@Composable
fun ListRoute(
    onClick: (Int) -> Unit,
    listViewModel: ListViewModel = hiltViewModel()
) {
    HandleEvents(listViewModel.event)

    val uiState by listViewModel.uiState.collectAsStateWithLifecycle()

    ListScreen(
        uiState = uiState,
        onRefresh = {
            listViewModel.acceptIntent(ListIntent.RefreshBooks)
        },
        onBookClicked = {
//            listViewModel.acceptIntent(ListIntent.BookClicked(it))
            onClick(it)
        }
    )

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListScreen(
    uiState: ListUiState,
    onRefresh: () -> Unit,
    onBookClicked: (Int) -> Unit
) {
    val systemUiController = rememberSystemUiController()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = {
            onRefresh()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        if (uiState.books.isNotEmpty()) {
            systemUiController.setSystemBarsColor(
                color = YacsaTheme.colors.primaryText
            )
            ListContent(
                uiState = uiState,
                onBookClicked = onBookClicked,
                modifier = Modifier.pullRefresh(pullRefreshState)
            )
        } else {
            systemUiController.setSystemBarsColor(
                color = YacsaTheme.colors.primaryBackground
            )
            ListNoContent(uiState = uiState)
        }

        PullRefreshIndicator(
            uiState.isLoading,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    uiState: ListUiState,
    onBookClicked: (Int) -> Unit
) {

    val state = rememberLazyListState()

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

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                // TODO: fix
                .padding(
                    horizontal = 16.dp
                ),
            flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
        ) {
            itemsIndexed(
                items = uiState.books,
                key = { _, item ->
                    item.id ?: 0
                }
            ) { index, item ->
                ListItem(
                    title = item.title ?: "",
                    description = item.authors?.firstOrNull()?.name?:"NI",
                    imageUrl = item.formats?.imageJpeg,
                    onListItemClick = {
                        item.id?.let { onBookClicked(it) }
                    }
                )
                if (index < uiState.books.lastIndex) {
                    Divider()
                }
            }
        }
    }
}


@Composable
fun ListNoContent(
    uiState: ListUiState,
) {
    when {
        uiState.isLoading -> {
            ListIsLoading()
        }
        uiState.isError -> {
            ListError()
        }
    }
}

@Composable
fun ListIsLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

//@Preview
//@Composable
//fun Preview_ListIsLoading() {
//    YacsaTheme {
//        ListIsLoading()
//    }
//}


@Composable
fun ListError(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "some error"
        )
    }
}

//@Preview
//@Composable
//fun Preview_ListError() {
//    YacsaTheme {
//        ListError()
//    }
//}


@Composable
private fun HandleEvents(events: Flow<ListEvent>) {
    val uriHandler = LocalUriHandler.current

    events.collectWithLifecycle {
        when (it) {
            is ListEvent.OnBookClick -> {
                // open detalization
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewListScreen() {
    YacsaTheme(useDarkTheme = true) {
//        ListScreen(){}
    }
}
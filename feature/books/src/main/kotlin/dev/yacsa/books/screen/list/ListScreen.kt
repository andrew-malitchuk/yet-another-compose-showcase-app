package dev.yacsa.books.screen.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.material.pullrefresh.PullRefreshDefaults
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.yacsa.books.screen.list.list.ListItem
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.platform.ext.collectWithLifecycle
import dev.yacsa.ui.theme.YacsaTheme
import kotlinx.coroutines.flow.Flow
import logcat.logcat

@Composable
fun ListRoute(
    onClick: () -> Unit,
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
            listViewModel.acceptIntent(ListIntent.BookClicked(it))
        }
    )

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListScreen(
    uiState: ListUiState,
    onRefresh: () -> Unit,
    onBookClicked: (String) -> Unit
) {

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = {
            onRefresh()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {

        if (uiState.books.isNotEmpty()) {
            ListContent(
                uiState = uiState,
                onBookClicked = onBookClicked
            )
        } else {
            ListNoContent(uiState = uiState)
        }

        PullRefreshIndicator(
            uiState.isLoading,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }

}

@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    uiState: ListUiState,
    onBookClicked: (String) -> Unit
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            // TODO: fix
            .padding(
                horizontal = 16.dp
            )
    ) {
        itemsIndexed(
            items = uiState.books,
            key = { _, item ->
                item.id ?: 0
            }
        ) { index, item ->
            ListItem(
                title = item.title ?: "",
                description = item.subjects.toString()
            )
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
                logcat("") { it.toString() }
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
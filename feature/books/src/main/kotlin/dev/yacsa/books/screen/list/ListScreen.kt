package dev.yacsa.books.screen.list

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.theapache64.rebugger.Rebugger
import dev.yacsa.books.screen.list.content.ContentError
import dev.yacsa.books.screen.list.content.ContentFetched
import dev.yacsa.books.screen.list.content.ContentIsLoading
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.platform.ext.collectWithLifecycle
import dev.yacsa.ui.theme.YacsaTheme
import dev.yacsa.ui.window.WindowInfo
import dev.yacsa.ui.window.rememberWindowInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import logcat.logcat

@Composable
fun ListRoute(
    onClick: (Int) -> Unit,
    onSearch: () -> Unit,
    notFound: () -> Unit,
    listViewModel: ListViewModel = hiltViewModel(),
) {
    HandleEvents(listViewModel.event, onClick, notFound)

    val uiState by listViewModel.uiState.collectAsStateWithLifecycle()

    val pagingState = listViewModel.pagingDataFlow?.collectAsLazyPagingItems()

    val windowInfo = rememberWindowInfo()

    Rebugger(
        trackMap = mapOf(
            "uiState" to uiState,
            "pagingState" to pagingState,
            "windowInfo" to windowInfo,
        ),
    )

    when (windowInfo.screenHeightInfo) {
        WindowInfo.WindowType.Compact -> {
            Toast.makeText(LocalContext.current, "Compact", Toast.LENGTH_SHORT).show()
        }

        WindowInfo.WindowType.Expanded -> {
            Toast.makeText(LocalContext.current, "Expanded", Toast.LENGTH_SHORT).show()
        }

        WindowInfo.WindowType.Medium -> {
            Toast.makeText(LocalContext.current, "Medium", Toast.LENGTH_SHORT).show()
        }
    }

    logcat("ListRoute") { "ListRoute" }

    if (uiState.isFeatureBlocked) {
        // https://github.com/googlecodelabs/android-navigation/issues/113
        LaunchedEffect(Unit) {
            notFound()
        }
    } else {
        ListScreen(
            onBookClicked = {
//                onClick(it)
                listViewModel.acceptIntent(ListIntent.BookClicked(it))
            },

            pagingState = pagingState,
            uiState = uiState,
            onSearch = onSearch,
        )
    }
}

@Composable
fun ListScreen(
    onBookClicked: (Int) -> Unit,
    pagingState: LazyPagingItems<BookUiModel>?,
    uiState: ListUiState,
    onSearch: () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    if (!uiState.isLoading && !uiState.isError && pagingState != null) {
        systemUiController.setSystemBarsColor(
            color = YacsaTheme.colors.primaryText,
        )
        ContentFetched(
            onBookClicked = onBookClicked,
            lazyPagingItems = pagingState,
            onSearch = onSearch,
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = YacsaTheme.colors.statusBarColor,
        )
        ListNoContent(uiState = uiState)
    }
}

@Composable
fun ListNoContent(
    uiState: ListUiState,
) {
    when {
        uiState.isLoading -> {
            ContentIsLoading()
        }

        uiState.isError -> {
            ContentError()
        }
    }
}

@Composable
private fun HandleEvents(
    events: Flow<ListEvent>,
    onBookClicked: (Int) -> Unit,
    notFound: () -> Unit,
) {
    events.collectWithLifecycle {
        when (it) {
            is ListEvent.OnBookClick -> {
                logcat("foo") { it.toString() }
                if (it.isBlocked) {
                    notFound()
                } else {
                    onBookClicked(it.bookId)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListScreen() {
    YacsaTheme(useDarkTheme = true) {
        ListScreen(
            {},
            flowOf(PagingData.empty<BookUiModel>()).collectAsLazyPagingItems(),
            ListUiState(),
            {},
        )
    }
}

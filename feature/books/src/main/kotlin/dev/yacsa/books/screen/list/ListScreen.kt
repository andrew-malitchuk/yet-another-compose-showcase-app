package dev.yacsa.books.screen.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.theapache64.rebugger.Rebugger
import dev.yacsa.books.screen.list.content.ContentFetched
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.platform.connection.ConnectivityObserver
import dev.yacsa.platform.ext.collectWithLifecycle
import dev.yacsa.ui.composable.content.ContentError
import dev.yacsa.ui.composable.content.ContentIsLoading
import dev.yacsa.ui.composable.snackbar.OfflineSnackbar
import dev.yacsa.ui.theme.YacsaTheme
import dev.yacsa.ui.theme.detectThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import logcat.logcat

@Composable
fun ListRoute(
    onClick: (Int) -> Unit,
    onSearch: () -> Unit,
    onSettings: () -> Unit,
    notFound: () -> Unit,
    onFavourite: () -> Unit,
    listViewModel: ListViewModel = hiltViewModel(),
) {
    HandleEvents(listViewModel.event, onClick, notFound)

    val uiState by listViewModel.uiState.collectAsStateWithLifecycle()

    val pagingState = listViewModel.pagingDataFlow?.collectAsLazyPagingItems()

    val status by listViewModel.connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )

    val systemUiController = rememberSystemUiController()

    Rebugger(
        trackMap = mapOf(
            "uiState" to uiState,
            "pagingState" to pagingState,
//            "windowInfo" to windowInfo,
        ),
    )

    val isOfflineMode = when (status) {
        ConnectivityObserver.Status.Available -> false
        else -> true
    }


//    when (windowInfo.screenHeightInfo) {
//        WindowInfo.WindowType.Compact -> {
//            Toast.makeText(LocalContext.current, "Compact", Toast.LENGTH_SHORT).show()
//        }
//
//        WindowInfo.WindowType.Expanded -> {
//            Toast.makeText(LocalContext.current, "Expanded", Toast.LENGTH_SHORT).show()
//        }
//
//        WindowInfo.WindowType.Medium -> {
//            Toast.makeText(LocalContext.current, "Medium", Toast.LENGTH_SHORT).show()
//        }
//    }

    logcat("ListRoute") { "ListRoute" }

    val currentTheme  by listViewModel.currentTheme
    val isDarkTheme = currentTheme?.detectThemeMode()?:false

    YacsaTheme(isDarkTheme) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            if (isOfflineMode) {
                systemUiController.setSystemBarsColor(
                    color = YacsaTheme.colors.primary,
                )
                OfflineSnackbar(message = "Offline mode")
            } else {
                systemUiController.setSystemBarsColor(
                    color = YacsaTheme.colors.background,
                )
            }


            if (uiState.isFeatureBlocked) {
                // https://github.com/googlecodelabs/android-navigation/issues/113
                LaunchedEffect(Unit) {
                    notFound()
                }
            } else {
                ListScreen(
                    onBookClicked = {
                        listViewModel.acceptIntent(ListIntent.BookClicked(it))
                    },
                    pagingState = pagingState,
                    uiState = uiState,
                    onSearch = onSearch,
                    onSettings = onSettings,
                    onFavourite = onFavourite,
                )
            }
        }
    }
}

@Composable
fun ListScreen(
    onBookClicked: (Int) -> Unit,
    pagingState: LazyPagingItems<BookUiModel>?,
    uiState: ListUiState,
    onSearch: () -> Unit,
    onSettings: () -> Unit,
    onFavourite: () -> Unit,
) {
    if (!uiState.isLoading && !uiState.isError && pagingState != null) {
        ContentFetched(
            onBookClicked = onBookClicked,
            lazyPagingItems = pagingState,
            onSearch = onSearch,
            onSettings = onSettings,
            onFavourite = onFavourite
        )
    } else {
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
            ContentError(errorMessage = "Moshi moshi?"){}
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
                if (it.isFeatureEnabled) {
                    onBookClicked(it.bookId)
                } else {
                    notFound()
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
            {},
            {}
        )
    }
}

package dev.yacsa.books.screen.list

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yacsa.platform.ext.collectWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import dev.yacsa.books.screen.list.content.ContentError
import dev.yacsa.books.screen.list.content.ContentFetched
import dev.yacsa.books.screen.list.content.ContentIsLoading
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.theme.YacsaTheme
import dev.yacsa.ui.window.WindowInfo
import dev.yacsa.ui.window.rememberWindowInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun ListRoute(
    onClick: (Int) -> Unit,
    listViewModel: ListViewModel = hiltViewModel()
) {
    HandleEvents(listViewModel.event)

    val uiState by listViewModel.uiState.collectAsStateWithLifecycle()

    val pagingState = listViewModel.pagingDataFlow?.collectAsLazyPagingItems()

    val windowInfo = rememberWindowInfo()

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


    ListScreen(
        onBookClicked = {
            onClick(it)
        },
        pagingState = pagingState,
        uiState = uiState,
    )

}


@Composable
fun ListScreen(
    onBookClicked: (Int) -> Unit,
    pagingState: LazyPagingItems<BookUiModel>?,
    uiState: ListUiState,
) {
    val systemUiController = rememberSystemUiController()

    if (!uiState.isLoading && !uiState.isError && pagingState != null) {
        systemUiController.setSystemBarsColor(
            color = YacsaTheme.colors.primaryText
        )
        ContentFetched(
            onBookClicked = onBookClicked,
            lazyPagingItems = pagingState,
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = YacsaTheme.colors.statusBarColor
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
        ListScreen(
            {},
            flowOf(PagingData.empty<BookUiModel>()).collectAsLazyPagingItems(),
            ListUiState()
        )
    }
}
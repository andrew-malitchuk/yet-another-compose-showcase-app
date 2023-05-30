package dev.yacsa.favourite.screen.favourite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yacsa.favourite.screen.favourite.content.ContentFetched
import dev.yacsa.favourite.screen.favourite.content.ListNoContent
import dev.yacsa.model.model.BookUiModel

@Composable
fun FavouriteRoute(
    favouriteViewModel: FavouriteViewModel = hiltViewModel(),
) {
    val uiState by favouriteViewModel.uiState.collectAsStateWithLifecycle()

    val foo = favouriteViewModel.flow?.collectAsStateWithLifecycle(null)

    FavouriteScreen(
        uiState,
        foo
    )
}

@Composable
fun FavouriteScreen(
    uiState: FavouriteUiState,
    foo: State<List<BookUiModel?>?>?
) {
    val systemUiController = rememberSystemUiController()

    if (!uiState.isLoading && !uiState.isError) {
        ContentFetched(
            foo
        )
    } else {
        ListNoContent(uiState = uiState)
    }
}

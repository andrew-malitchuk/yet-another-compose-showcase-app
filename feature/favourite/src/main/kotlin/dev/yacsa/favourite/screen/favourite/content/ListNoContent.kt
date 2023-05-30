package dev.yacsa.favourite.screen.favourite.content

import androidx.compose.runtime.Composable
import dev.yacsa.favourite.screen.favourite.FavouriteUiState
import dev.yacsa.ui.composable.content.ContentError
import dev.yacsa.ui.composable.content.ContentIsLoading


@Composable
fun ListNoContent(
    uiState: FavouriteUiState,
) {
    when {
        uiState.isLoading -> {
            ContentIsLoading()
        }

        uiState.isError -> {
            ContentError(errorMessage = "Moshi moshi?")
        }
    }
}
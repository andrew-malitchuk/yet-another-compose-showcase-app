package dev.yacsa.favourite.screen.favourite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FavouriteRoute(
    favouriteViewModel: FavouriteViewModel = hiltViewModel(),
) {
    val uiState by favouriteViewModel.uiState.collectAsStateWithLifecycle()

    FavouriteScreen(
        uiState,
    )
}

@Composable
fun FavouriteScreen(
    uiState: FavouriteUiState
){

}

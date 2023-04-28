package dev.yacsa.search.screen.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yacsa.search.screen.search.content.ContentError
import dev.yacsa.search.screen.search.content.ContentFetched
import dev.yacsa.ui.theme.YacsaTheme


@Composable
fun SearchRoute(
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by searchViewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        uiState,
    )
}

@Composable
fun SearchScreen(
    uiState: SearchUiState,
) {
    val systemUiController = rememberSystemUiController()
    if (!uiState.isLoading && !uiState.isError) {
        systemUiController.setSystemBarsColor(
            color = YacsaTheme.colors.primaryText,
        )
        ContentFetched(

        )
    } else {
        ContentError()
    }

}

@Composable
@Preview(showBackground = true)
fun Preview_SearchScreen() {
    YacsaTheme() {
        SearchScreen(
            SearchUiState(),
        )
    }
}

package dev.yacsa.search.screen.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yacsa.search.screen.search.content.ContentError
import dev.yacsa.search.screen.search.content.ContentFetched
import dev.yacsa.search.screen.search.content.ContentLoading
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun SearchRoute(
    searchViewModel: SearchViewModel = hiltViewModel(),
    onBookClicked: (Int) -> Unit,
) {
    val uiState by searchViewModel.uiState.collectAsStateWithLifecycle()
    val searchText by searchViewModel.searchText.collectAsState()

    SearchScreen(
        uiState,
        searchText,
        onValueChange = {
            searchViewModel.searchText.value = it
        },
        onBookClicked,
        onDelete = {
            searchViewModel.acceptIntent(SearchIntent.ClearSearch)
        },
    )
}

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    searchText: String,
    onValueChange: (String) -> Unit,
    onBookClicked: (Int) -> Unit,
    onDelete: () -> Unit,
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setSystemBarsColor(
            color = YacsaTheme.colors.secondaryBackground,
        )
        setNavigationBarColor(
            color = YacsaTheme.colors.secondaryBackground,
        )
    }
    if (uiState.isContentLoading) {
        ContentLoading()
    } else {
        if (!uiState.isContentLoading && uiState.topSearch != null) {
            ContentFetched(
                uiState = uiState,
                searchText = searchText,
                onValueChange = onValueChange,
                onBookClicked = onBookClicked,
                onDelete = onDelete,
            )
        }else{
            if(uiState.isError){
                ContentError()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_SearchScreen() {
    YacsaTheme() {
        SearchScreen(
            SearchUiState(),
            "",
            {},
            {},
            {},
        )
    }
}

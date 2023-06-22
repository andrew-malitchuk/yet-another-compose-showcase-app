package dev.yacsa.search.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.theapache64.rebugger.Rebugger
import dev.yacsa.platform.connection.ConnectivityObserver
import dev.yacsa.platform.string.UiText
import dev.yacsa.search.screen.search.content.ContentFetched
import dev.yacsa.search.screen.search.dialog.FilterDialogResult
import dev.yacsa.ui.composable.content.ContentError
import dev.yacsa.ui.composable.content.ContentIsLoading
import dev.yacsa.ui.composable.snackbar.OfflineSnackbar
import dev.yacsa.ui.composable.theme.detectThemeMode
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun SearchRoute(
    searchViewModel: SearchViewModel = hiltViewModel(),
    onBookClicked: (Int) -> Unit,
    onBackClick: () -> Unit,
) {
    val uiState by searchViewModel.uiState.collectAsStateWithLifecycle()
    val searchText by searchViewModel.searchText.collectAsState()

    val previousContent by searchViewModel.filterResult.collectAsState()
    val currentTheme by searchViewModel.currentTheme
    val isDarkTheme = currentTheme?.detectThemeMode() ?: false

    val status by searchViewModel.connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable,
    )
    val isOfflineMode = when (status) {
        ConnectivityObserver.Status.Available -> false
        else -> true
    }
    val systemUiController = rememberSystemUiController()

    Rebugger(
        trackMap = mapOf(
            "uiState" to uiState,
            "currentTheme" to currentTheme,
            "isDarkTheme" to isDarkTheme,
            "previousContent" to previousContent,
            "searchText" to searchText,
        ),
    )

    YacsaTheme(isDarkTheme) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (isOfflineMode) {
                systemUiController.setSystemBarsColor(
                    color = YacsaTheme.colors.primary,
                )
                OfflineSnackbar(message = UiText.StringResource(dev.yacsa.localization.R.string.errors_offline).asString())
            } else {
                systemUiController.setSystemBarsColor(
                    color = YacsaTheme.colors.background,
                )
            }
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
                onFilterChanged = {
                    searchViewModel.filterResult.value = it
                    searchViewModel.acceptIntent(SearchIntent.Search(searchText))
                },
                previousContent = previousContent,
                onBackClick = onBackClick,
            )
        }
    }
}

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    searchText: String,
    onValueChange: (String) -> Unit,
    onBookClicked: (Int) -> Unit,
    onDelete: () -> Unit,
    onFilterChanged: (FilterDialogResult) -> Unit,
    previousContent: FilterDialogResult?,
    onBackClick: () -> Unit,
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setSystemBarsColor(
            color = YacsaTheme.colors.background,
        )
        setNavigationBarColor(
            color = YacsaTheme.colors.background,
        )
    }
    if (uiState.isContentLoading) {
        ContentIsLoading()
    } else {
        if (!uiState.isContentLoading && uiState.topSearch != null) {
            ContentFetched(
                uiState = uiState,
                searchText = searchText,
                onValueChange = onValueChange,
                onBookClicked = onBookClicked,
                onDelete = onDelete,
                onFilterChanged = onFilterChanged,
                previousContent = previousContent,
                onBackClick = onBackClick,

            )
        } else {
            if (uiState.isError) {
                ContentError(errorMessage = UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).asString()) { }
            } else {
                if (uiState.isResultLoading) {
                    ContentIsLoading()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_SearchScreen_Light() {
    YacsaTheme(false) {
        SearchScreen(
            SearchUiState(),
            "",
            {},
            {},
            {},
            {},
            null,
            {},
        )
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_SearchScreen_Dark() {
    YacsaTheme(true) {
        SearchScreen(
            SearchUiState(),
            "",
            {},
            {},
            {},
            {},
            null,
            {},
        )
    }
}

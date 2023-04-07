package dev.yacsa.books.screen.detalization

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yacsa.books.screen.list.ListUiState
import dev.yacsa.books.screen.list.ListViewModel
import logcat.logcat

@Composable
fun DetalizationRoute(
    detalizationViewModel: DetalizationViewModel = hiltViewModel(),
    bookId: Int?
) {

    logcat("foo") { bookId.toString() }

}

@Composable
fun DetalizationScreen(
    uiState: DetalizationUiState,
) {

}
package dev.yacsa.books.screen.detalization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.theapache64.rebugger.Rebugger
import dev.yacsa.books.screen.detalization.content.ContentFetchedPortrait
import dev.yacsa.books.screen.detalization.content.ContentIsLoading
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun DetalizationRoute(
    detalizationViewModel: DetalizationViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val uiState by detalizationViewModel.uiState.collectAsStateWithLifecycle()

    Rebugger(
        trackMap = mapOf(
            "uiState" to uiState,
        ),
    )

    DetalizationScreen(
        uiState = uiState,
        onBackClick = onBackClick,
    )
}

@Composable
fun DetalizationScreen(
    uiState: DetalizationUiState,
    onBackClick: () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    when {
        uiState.isError -> {
        }

        uiState.isLoading -> {
            systemUiController.apply {
                setSystemBarsColor(
                    color = Color.White,
                )
                setNavigationBarColor(
                    color = Color.White,
                )
            }
            ContentIsLoading()
        }

        else -> {
            systemUiController.apply {
                setSystemBarsColor(
                    color = Color.LightGray,
                )
                setNavigationBarColor(
                    color = Color.White,
                )
            }
            ContentFetchedPortrait(book = uiState.book, onBackClick = { onBackClick() })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_DetalizationScreen() {
    YacsaTheme() {
        DetalizationScreen(
            DetalizationUiState(isLoading = false, isError = false),
            onBackClick = {},
        )
    }
}

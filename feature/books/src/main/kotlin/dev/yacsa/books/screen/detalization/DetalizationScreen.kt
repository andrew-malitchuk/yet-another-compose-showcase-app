package dev.yacsa.books.screen.detalization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.theapache64.rebugger.Rebugger
import dev.yacsa.books.screen.detalization.content.ContentFetchedPortrait
import dev.yacsa.books.screen.detalization.content.ContentIsLoading
import dev.yacsa.platform.ext.collectWithLifecycle
import dev.yacsa.ui.theme.YacsaTheme
import kotlinx.coroutines.flow.Flow

@Composable
fun DetalizationRoute(
    detalizationViewModel: DetalizationViewModel = hiltViewModel(),
    bookId: Int?,
    onBackClick: () -> Unit,
) {
    HandleEvents(detalizationViewModel.event)
    val uiState by detalizationViewModel.uiState.collectAsStateWithLifecycle()

    Rebugger(
        trackMap = mapOf(
            "uiState" to uiState,
        ),
    )
    detalizationViewModel.foo()

    DetalizationScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onFormatClick = {
            detalizationViewModel.acceptIntent(DetalizationIntent.OnLinkClick(it))
        }
    )
}

@Composable
fun DetalizationScreen(
    uiState: DetalizationUiState,
    onBackClick: () -> Unit,
    onFormatClick: (String) -> Unit,
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
            ContentFetchedPortrait(
                book = uiState.book,
                onBackClick = { onBackClick() },
                onFormatClick = { onFormatClick(it) },
                onAuthorClick = {},
                onTranslatorClick = {},
                onLanguageClick = {},
                onSubjectClick = {},
                onBookshelfClick = {}
            )
        }
    }
}


@Composable
private fun HandleEvents(events: Flow<DetalizationEvent>) {
    val uriHandler = LocalUriHandler.current

    events.collectWithLifecycle {
        when (it) {
            is DetalizationEvent.OpenWebBrowserWithDetails -> {
                uriHandler.openUri(it.uri)
            }
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
            onFormatClick={}
        )
    }
}

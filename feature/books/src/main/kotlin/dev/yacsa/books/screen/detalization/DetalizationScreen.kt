package dev.yacsa.books.screen.detalization

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.theapache64.rebugger.Rebugger
import dev.yacsa.books.screen.detalization.content.ContentFetchedPortrait
import dev.yacsa.books.screen.utils.share
import dev.yacsa.platform.connection.ConnectivityObserver
import dev.yacsa.platform.ext.collectWithLifecycle
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.composable.content.ContentIsLoading
import dev.yacsa.ui.composable.snackbar.OfflineSnackbar
import dev.yacsa.ui.composable.theme.detectThemeMode
import dev.yacsa.ui.theme.YacsaTheme
import kotlinx.coroutines.flow.Flow
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun DetalizationRoute(
    detalizationViewModel: DetalizationViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    HandleEvents(detalizationViewModel.event)
    val uiState by detalizationViewModel.uiState.collectAsStateWithLifecycle()
    detalizationViewModel.checked.also {
        it.value?.let { it1 -> detalizationViewModel.changeFavourite(it1) }
    }

    val currentTheme by detalizationViewModel.currentTheme
    val isDarkTheme = currentTheme?.detectThemeMode() ?: false

    val status by detalizationViewModel.connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
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
        ),
    )

    YacsaTheme(isDarkTheme) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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

            DetalizationScreen(
                uiState = uiState,
                onBackClick = onBackClick,
                onFormatClick = {
                    detalizationViewModel.acceptIntent(DetalizationIntent.OnLinkClick(it))
                },
                favourite = detalizationViewModel.checked,
                favouriteState = detalizationViewModel.favouriteState,
                onShareClick = {
                    detalizationViewModel.acceptIntent(DetalizationIntent.OnShareClick(it))
                }
            )
        }
    }
}

@Composable
fun DetalizationScreen(
    uiState: DetalizationUiState,
    onBackClick: () -> Unit,
    onFormatClick: (String) -> Unit,
    favourite: MutableState<Boolean?>,
    favouriteState: MutableState<Boolean?>,
    onShareClick: (Int) -> Unit
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
                    color = YacsaTheme.colors.surface,
                )
                setNavigationBarColor(
                    color = YacsaTheme.colors.background,
                )
            }
            Box() {
                ContentFetchedPortrait(
                    book = uiState.book,
                    onBackClick = { onBackClick() },
                    onFormatClick = { onFormatClick(it) },
                    onAuthorClick = {},
                    onTranslatorClick = {},
                    onLanguageClick = {},
                    onSubjectClick = {},
                    onBookshelfClick = {},
                    favourite = favourite,
                    onShareClick = onShareClick
                )
                if (favourite.value == true && favouriteState.value == true) {
                    KonfettiView(
                        modifier = Modifier.fillMaxSize(),
                        parties = listOf(
                            Party(
                                emitter = Emitter(
                                    duration = 1,
                                    TimeUnit.SECONDS
                                ).perSecond(30),
                                position = Position.Relative(0.5, 0.0)
                            )
                        ),
                    )
                }
            }
        }
    }
}


@Composable
private fun HandleEvents(events: Flow<DetalizationEvent>) {
    val uriHandler = LocalUriHandler.current

    val context = LocalContext.current

    events.collectWithLifecycle {
        when (it) {
            is DetalizationEvent.OpenWebBrowserWithDetails -> {
                uriHandler.openUri(it.uri)
            }

            is DetalizationEvent.ShareDeeplink -> {
                context.share(it.uri, "foo")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_DetalizationScreen_Light() {
    YacsaTheme(false) {
        DetalizationScreen(
            DetalizationUiState(isLoading = false, isError = false),
            onBackClick = {},
            onFormatClick = {},
            remember { mutableStateOf(false) },
            remember { mutableStateOf(false) },
            onShareClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_DetalizationScreen_Dark() {
    YacsaTheme(true) {
        DetalizationScreen(
            DetalizationUiState(isLoading = false, isError = false),
            onBackClick = {},
            onFormatClick = {},
            remember { mutableStateOf(false) },
            remember { mutableStateOf(false) },
            onShareClick = {}
        )
    }
}

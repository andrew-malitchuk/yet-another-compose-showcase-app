package dev.yacsa.settings.screen.settings

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.theapache64.rebugger.Rebugger
import dev.yacsa.model.model.theme.ThemeUiModel
import dev.yacsa.platform.ext.collectWithLifecycle
import dev.yacsa.platform.localization.LocaleUtils
import dev.yacsa.platform.string.UiText
import dev.yacsa.settings.screen.settings.content.ContentFetched
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.content.ContentError
import dev.yacsa.ui.composable.content.ContentIsLoading
import dev.yacsa.ui.composable.divider.AnimatedDivider
import dev.yacsa.ui.composable.theme.CircularReveal
import dev.yacsa.ui.composable.theme.detectThemeMode
import dev.yacsa.ui.theme.YacsaTheme
import io.github.serpro69.kfaker.Faker
import kotlinx.coroutines.flow.Flow

@Composable
fun SettingsRoute(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onFfClick: () -> Unit,
    onAnalyticsClick: () -> Unit,
    onDeeplinkClick: () -> Unit,
    onInfoClick: () -> Unit,
) {
    HandleEvents(settingsViewModel.event)

    val uiState by settingsViewModel.uiState.collectAsStateWithLifecycle()
    val currentTheme by settingsViewModel.currentTheme
    val isDarkTheme = currentTheme?.detectThemeMode() ?: false

    val theme: MutableState<ThemeUiModel?> = settingsViewModel.currentTheme

    theme.value?.let {
        settingsViewModel.changeTheme(it)
    }

    val lang: MutableState<String?> = remember {
        mutableStateOf(uiState.lang)
    }

    lang.value?.let {
        settingsViewModel.acceptIntent(SettingsIntent.SetLang(it))
    }

    Rebugger(
        trackMap = mapOf(
            "uiState" to uiState,
            "currentTheme" to currentTheme,
            "isDarkTheme" to isDarkTheme,
            "theme" to theme,
        ),
    )

    CircularReveal(
        targetState = isDarkTheme,
        animationSpec = tween(500),
    ) { isDark ->
        YacsaTheme(isDark) {
            SettingsScreen(
                uiState,
                onBackClick,
                onFfClick,
                onAnalyticsClick,
                onDeeplinkClick,
                theme,
                lang,
                onInfoClick,
            )
        }
    }
}

@Composable
private fun HandleEvents(events: Flow<SettingsEvent>) {
    val context = LocalContext.current

    events.collectWithLifecycle {
        when (it) {
            is SettingsEvent.ChangeLang -> {
                LocaleUtils.setLocale(context, it.lang)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onBackClick: () -> Unit,
    onFfClick: () -> Unit,
    onAnalyticsClick: () -> Unit,
    onDeeplinkClick: () -> Unit,
    theme: MutableState<ThemeUiModel?>,
    lang: MutableState<String?>,
    onInfoClick: () -> Unit,
) {
    val systemUiController = rememberSystemUiController()
    val state = rememberLazyListState()

    systemUiController.apply {
        setSystemBarsColor(
            color = YacsaTheme.colors.surface,
        )
        setNavigationBarColor(
            color = YacsaTheme.colors.background,
        )
    }
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topAppBarState)

    if (uiState.isLoading) {
        ContentIsLoading()
    } else {
        if (uiState.isError) {
            ContentError(
                errorMessage = UiText.StringResource(dev.yacsa.localization.R.string.errors_sww)
                    .asString(),
            ) {
            }
        } else {
            Scaffold(
                modifier = Modifier
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    LargeTopAppBar(
                        title = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = UiText.StringResource(dev.yacsa.localization.R.string.settings_settings)
                                        .asString(),
                                    style = YacsaTheme.typography.header,
                                    color = YacsaTheme.colors.primary,
                                )
                                Spacer(modifier = Modifier.width(YacsaTheme.spacing.small))
                                Icon(
                                    painterResource(id = R.drawable.icon_gear_six_bold_24),
                                    contentDescription = null,
                                    tint = YacsaTheme.colors.accent,
                                )
                            }
                        },
                        navigationIcon = {
                            SmallFloatingActionButton(
                                onClick = { onBackClick() },
                                containerColor = YacsaTheme.colors.accent,
                                elevation = FloatingActionButtonDefaults.elevation(
                                    0.dp,
                                    0.dp,
                                    0.dp,
                                    0.dp,
                                ),
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_caret_left_regular_24),
                                    contentDescription = null,
                                    tint = YacsaTheme.colors.primary,
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior,
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = YacsaTheme.colors.surface,
                        ),
                    )
                    Column {
                        Spacer(modifier = Modifier.height(YacsaTheme.spacing.extraLarge))
                        AnimatedDivider(state = state)
                    }
                },
            ) { innerPadding ->
                ContentFetched(
                    innerPadding = innerPadding,
                    state = state,
                    topAppBarState = topAppBarState,
                    onFfClick = onFfClick,
                    onAnalyticsClick = onAnalyticsClick,
                    onDeeplinkClick = onDeeplinkClick,
                    theme = theme,
                    language = lang,
                    onInfoClick = onInfoClick,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_SettingsScreen_Light() {
    val faker = Faker()
    YacsaTheme(false) {
        SettingsScreen(
            uiState = SettingsUiState(lang = "uk"),
            onBackClick = {},
            onFfClick = {},
            onAnalyticsClick = {},
            theme = remember { mutableStateOf(null) },
            lang = remember { mutableStateOf(null) },
            onDeeplinkClick = {},
            onInfoClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_SettingsScreen_Dark() {
    val faker = Faker()
    YacsaTheme(true) {
        SettingsScreen(
            uiState = SettingsUiState(lang = "uk"),
            onBackClick = {},
            onFfClick = {},
            onAnalyticsClick = {},
            onDeeplinkClick = {},
            theme = remember { mutableStateOf(null) },
            lang = remember { mutableStateOf(null) },
            onInfoClick = {},
        )
    }
}

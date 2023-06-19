package dev.yacsa.analytics.screen.analytics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.theapache64.rebugger.Rebugger
import dev.yacsa.analytics.screen.analytics.content.ContentFetched
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.divider.AnimatedDivider
import dev.yacsa.ui.composable.theme.detectThemeMode
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun AnalyticsRoute(
    analyticsViewModel: AnalyticsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val uiState by analyticsViewModel.uiState.collectAsStateWithLifecycle()
    val currentTheme  by analyticsViewModel.currentTheme
    val isDarkTheme = currentTheme?.detectThemeMode()?:false

    Rebugger(
        trackMap = mapOf(
            "uiState" to uiState,
            "currentTheme" to currentTheme,
            "isDarkTheme" to isDarkTheme,
        ),
    )

    YacsaTheme(isDarkTheme) {
        AnalyticsScreen(
            uiState,
            onBackClick,
            onDeleteClick = {
                analyticsViewModel.acceptIntent(AnalyticsIntent.Delete)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(
    uiState: AnalyticsUiState,
    onBackClick: () -> Unit,
    onDeleteClick:()->Unit
) {
    val systemUiController = rememberSystemUiController()
    val state = rememberLazyListState()

    systemUiController.apply {
        setSystemBarsColor(
            color = YacsaTheme.colors.statusBar,
        )
        setNavigationBarColor(
            color = YacsaTheme.colors.surface,
        )
    }
    val foo = rememberTopAppBarState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(foo)


    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        // TODO: fix
                        Text(
                            text = UiText.StringResource(dev.yacsa.localization.R.string.settings_analytics).asString(),
                            style = YacsaTheme.typography.header,
                            color = YacsaTheme.colors.primary
                        )
                        // TODO: fix
                        Spacer(modifier = Modifier.width(YacsaTheme.spacing.small))
                        androidx.compose.material3.Icon(
                            painterResource(id = R.drawable.icon_flask_bold_24),
                            contentDescription = null,
                            tint = YacsaTheme.colors.accent
                        )
                    }
                },
                navigationIcon = {
                    SmallFloatingActionButton(
                        onClick = { onBackClick() },
                        containerColor = YacsaTheme.colors.accent,
                        elevation = FloatingActionButtonDefaults.elevation(0.dp,0.dp,0.dp,0.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_caret_left_regular_24),
                            contentDescription = null,
                            tint = YacsaTheme.colors.primary
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = YacsaTheme.colors.background,
                ),
            )
            Column {
                Spacer(modifier = Modifier.height(YacsaTheme.spacing.extraLarge))
                AnimatedDivider(state = state)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onDeleteClick() },
                backgroundColor = YacsaTheme.colors.accent,
                elevation = androidx.compose.material.FloatingActionButtonDefaults.elevation(0.dp,0.dp,0.dp,0.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_trash_bold_24),
                    contentDescription = null,
                    tint = YacsaTheme.colors.primary
                )
            }
        }
    ) { innerPadding ->
        ContentFetched(
            innerPadding = innerPadding,
            state = state,
            foo = foo,
            uiState=uiState
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_SettingsScreen_Light() {
    YacsaTheme(false){
        AnalyticsScreen(
            uiState = AnalyticsUiState(),
            onBackClick = {},
            onDeleteClick={}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_SettingsScreen_Dark() {
    YacsaTheme(true){
        AnalyticsScreen(
            uiState = AnalyticsUiState(),
            onBackClick = {},
            onDeleteClick={}
        )
    }
}

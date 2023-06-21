package dev.yacsa.info.screen.info

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
import dev.yacsa.info.screen.info.content.ContentFetched
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.divider.AnimatedDivider
import dev.yacsa.ui.composable.theme.detectThemeMode
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun InfoRoute(
    infoViewModel: InfoViewModel = hiltViewModel(),
    onBackClick:()->Unit
) {
    val uiState by infoViewModel.uiState.collectAsStateWithLifecycle()

    val currentTheme  by infoViewModel.currentTheme
    val isDarkTheme = currentTheme?.detectThemeMode()?:false

    Rebugger(
        trackMap = mapOf(
            "uiState" to uiState,
            "currentTheme" to currentTheme,
            "isDarkTheme" to isDarkTheme,
        ),
    )

    YacsaTheme(isDarkTheme) {
        InfoScreen(
            uiState,
            onBackClick = onBackClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    uiState: InfoUiState,
    onBackClick:()->Unit,
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
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topAppBarState)


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
                            text = UiText.StringResource(dev.yacsa.localization.R.string.settings_info).asString(),
                            style = YacsaTheme.typography.header,
                            color = YacsaTheme.colors.primary
                        )
                        Spacer(modifier = Modifier.width(YacsaTheme.spacing.small))
                        androidx.compose.material3.Icon(
                            painterResource(id = R.drawable.icon_info_regular_24),
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
    ) {innerPadding ->
        ContentFetched(
            innerPadding = innerPadding,
            state = state,
            topAppBarState = topAppBarState,
            uiState=uiState,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_InfoScreen_Light() {
    YacsaTheme(false) {
        InfoScreen(
            InfoUiState(),
            {},
        )
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_InfoScreen_Dark() {
    YacsaTheme(true) {
        InfoScreen(
            InfoUiState(),
            {},
        )
    }
}

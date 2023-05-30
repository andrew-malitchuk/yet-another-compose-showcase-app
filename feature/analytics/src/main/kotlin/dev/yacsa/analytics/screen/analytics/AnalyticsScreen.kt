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
import dev.yacsa.analytics.screen.analytics.content.ContentFetched
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.divider.AnimatedDivider
import dev.yacsa.ui.theme.YacsaTheme
import logcat.logcat

@Composable
fun AnalyticsRoute(
    analyticsViewModel: AnalyticsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val uiState by analyticsViewModel.uiState.collectAsStateWithLifecycle()

    AnalyticsScreen(
        uiState,
        onBackClick,
        onDeleteClick = {
            analyticsViewModel.acceptIntent(AnalyticsIntent.Delete)
        }
    )
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
            color = YacsaTheme.colors.secondaryBackground,
        )
        setNavigationBarColor(
            color = YacsaTheme.colors.secondaryBackground,
        )
    }
    val foo = rememberTopAppBarState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(foo)

    logcat("foo") { foo.collapsedFraction.toString() }

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
                        Text(text = "Analytics", style = YacsaTheme.typography.heading)
                        // TODO: fix
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painterResource(id = R.drawable.icon_flask_bold_24),
                            contentDescription = null,
                        )
                    }
                },
                navigationIcon = {
                    SmallFloatingActionButton(onClick = { onBackClick() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_caret_left_regular_24),
                            contentDescription = null,
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = YacsaTheme.colors.primaryBackground,
                ),
            )
            Column {
                Spacer(modifier = Modifier.height(64.dp))
                AnimatedDivider(state = state)
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onDeleteClick()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_trash_bold_24),
                    contentDescription = null,
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
fun Preview_SettingsScreen() {
    YacsaTheme {
        AnalyticsScreen(
            uiState = AnalyticsUiState(),
            onBackClick = {},
            onDeleteClick={}
        )
    }
}
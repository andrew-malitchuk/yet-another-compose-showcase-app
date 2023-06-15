package dev.yacsa.deeplink.screen.deeplink

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
import dev.yacsa.deeplink.screen.deeplink.content.ContentFetched
import dev.yacsa.platform.ext.triggerDeepLink
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.divider.AnimatedDivider
import dev.yacsa.ui.composable.theme.detectThemeMode
import dev.yacsa.ui.theme.YacsaTheme
import logcat.logcat

@Composable
fun DeeplinkRoute(
    DeeplinkViewModel: DeeplinkViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val uiState by DeeplinkViewModel.uiState.collectAsStateWithLifecycle()

    val currentTheme  by DeeplinkViewModel.currentTheme
    val isDarkTheme = currentTheme?.detectThemeMode()?:false

    val onValueChange:MutableState<String> = remember{ mutableStateOf("") }

    val foo = LocalContext.current

    YacsaTheme(isDarkTheme) {
        DeeplinkScreen(
            uiState,
            onBackClick,
            onValueChange,
            onDeeplinkRun={
                foo.triggerDeepLink(onValueChange.value)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeeplinkScreen(
    uiState: DeeplinkUiState,
    onBackClick: () -> Unit,
    onValueChange:MutableState<String>,
    onDeeplinkRun:()->Unit
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
                        Text(
                            text = "Deeplink",
                            style = YacsaTheme.typography.header,
                            color = YacsaTheme.colors.primary
                        )
                        Spacer(modifier = Modifier.width(YacsaTheme.spacing.small))
                        androidx.compose.material3.Icon(
                            painterResource(id = R.drawable.icon_link_bold_24),
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
        }
    ) { innerPadding ->
        ContentFetched(
            innerPadding = innerPadding,
            state = state,
            foo = foo,
            uiState=uiState,
            foobar = onValueChange,
            onDeeplinkRun=onDeeplinkRun
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_SettingsScreen() {
    YacsaTheme {
        DeeplinkScreen(
            uiState = DeeplinkUiState(),
            onBackClick = {},
            onValueChange = remember {
                mutableStateOf("")
            },
            onDeeplinkRun={}
        )
    }
}

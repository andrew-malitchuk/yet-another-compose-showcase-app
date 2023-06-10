package dev.yacsa.favourite.screen.favourite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.yacsa.favourite.screen.favourite.content.ContentFetched
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.divider.AnimatedDivider
import dev.yacsa.ui.theme.YacsaTheme
import dev.yacsa.ui.theme.detectThemeMode

@Composable
fun FavouriteRoute(
    favouriteViewModel: FavouriteViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by favouriteViewModel.uiState.collectAsStateWithLifecycle()

    val foo = favouriteViewModel.flow?.collectAsStateWithLifecycle(null)

    val currentTheme  by favouriteViewModel.currentTheme
    val isDarkTheme = currentTheme?.detectThemeMode()?:false

    YacsaTheme(isDarkTheme) {
        FavouriteScreen(
            uiState,
            foo,
            onBackClick,
            onFavouriteMark = { id, isFavourite ->
                favouriteViewModel.acceptIntent(FavouriteIntent.MarkFavourite(id, isFavourite))
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    uiState: FavouriteUiState,
    favouriteFlow: State<List<BookUiModel?>?>?,
    onBackClick: () -> Unit,
    onFavouriteMark: (Int, Boolean) -> Unit
) {
    val foo = rememberTopAppBarState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(foo)
    val state = rememberLazyListState()


    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background( YacsaTheme.colors.background),
        topBar = {
            LargeTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        // TODO: fix
                        Text(
                            text = "Favourite",
                            style = YacsaTheme.typography.header,
                            color = YacsaTheme.colors.primary
                        )
                        // TODO: fix
                        Spacer(modifier = Modifier.width(8.dp))
                        androidx.compose.material3.Icon(
                            painterResource(id = R.drawable.icon_heart_regulat_24),
                            contentDescription = null,
                            tint = YacsaTheme.colors.accent
                        )
                    }
                },
                navigationIcon = {
                    SmallFloatingActionButton(
                        onClick = { onBackClick() },
                        containerColor = YacsaTheme.colors.accent
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
                Spacer(modifier = Modifier.height(64.dp))
                AnimatedDivider(state = state)
            }
        },
    ) { innerPadding ->
        ContentFetched(
            innerPadding = innerPadding,
            lazyListState = state,
            topAppBarState = foo,
            uiState = uiState,
            favouriteFlow = favouriteFlow,
            onFavouriteMark = onFavouriteMark
        )
    }
}

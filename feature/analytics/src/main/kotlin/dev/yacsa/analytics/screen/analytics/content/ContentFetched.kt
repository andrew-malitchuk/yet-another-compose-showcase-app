package dev.yacsa.analytics.screen.analytics.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.yacsa.analytics.screen.analytics.AnalyticsUiState
import dev.yacsa.analytics.screen.analytics.item.AnalyticsItem
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.content.ContentError
import dev.yacsa.ui.composable.content.ContentIsLoading
import dev.yacsa.ui.composable.content.ContentNoData
import dev.yacsa.ui.theme.YacsaTheme
import java.lang.Math.abs

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSnapperApi::class)
@Composable
fun ContentFetched(
    modifier: Modifier = Modifier.fillMaxSize(),
    innerPadding: PaddingValues,
    state: LazyListState,
    foo: TopAppBarState,
    uiState: AnalyticsUiState,
) {
    val corner = YacsaTheme.corners.medium - (YacsaTheme.corners.medium * abs(foo.collapsedFraction))
    val systemUiController = rememberSystemUiController()


    when {
        uiState.isLoading -> {
            systemUiController.setNavigationBarColor(
                color = YacsaTheme.colors.navigationBar,
            )
            ContentIsLoading()
        }

        uiState.isError -> {
            systemUiController.setNavigationBarColor(
                color = YacsaTheme.colors.statusBar,
            )
            ContentError(
                errorMessage = UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).asString()
            ){

            }
        }

        else -> {
            systemUiController.apply {
                setSystemBarsColor(
                    color = YacsaTheme.colors.statusBar,
                )
                setNavigationBarColor(
                    color = YacsaTheme.colors.surface,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(YacsaTheme.colors.background)
                    .padding(innerPadding),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = corner, topEnd = corner))
                        .background(YacsaTheme.colors.surface)
                ) {

                    if(uiState.analytics.isEmpty()){
                        ContentNoData(
                            message = UiText.StringResource(dev.yacsa.localization.R.string.errors_no_data).asString()
                        )

                    }else {

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            state = state,
                            contentPadding = PaddingValues(YacsaTheme.spacing.small),
                            verticalArrangement = Arrangement.spacedBy(YacsaTheme.spacing.small),
                        ) {
                            items(items = uiState.analytics) { item ->
                                AnalyticsItem(
                                    key = item.key.toString(),
                                    value = item.value.toString(),
                                    icon = R.drawable.icon_bell_regular_24,
                                    onClick = {
                                    },
                                )
                            }

                        }
                    }
                }
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Preview_ContentFetched_Light() {
    YacsaTheme(false) {
        ContentFetched(
            innerPadding = PaddingValues(YacsaTheme.spacing.small),
            state = rememberLazyListState(),
            foo = rememberTopAppBarState(),
            uiState = AnalyticsUiState(isError = true),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Preview_ContentFetched_Dark() {
    YacsaTheme(true) {
        ContentFetched(
            innerPadding = PaddingValues(YacsaTheme.spacing.small),
            state = rememberLazyListState(),
            foo = rememberTopAppBarState(),
            uiState = AnalyticsUiState(isError = true),
        )
    }
}

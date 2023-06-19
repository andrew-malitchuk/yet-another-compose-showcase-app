package dev.yacsa.favourite.screen.favourite.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yacsa.favourite.screen.favourite.FavouriteUiState
import dev.yacsa.favourite.screen.favourite.item.ItemFetchedList
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.composable.content.ContentError
import dev.yacsa.ui.composable.content.ContentIsLoading
import dev.yacsa.ui.composable.content.ContentNoData
import dev.yacsa.ui.theme.YacsaTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ContentFetched(
    favouriteFlow: State<List<BookUiModel?>?>?,
    uiState: FavouriteUiState,
    innerPadding: PaddingValues,
    topAppBarState: TopAppBarState,
    lazyListState: LazyListState,
    onFavouriteMark: (Int, Boolean) -> Unit
) {

    val corner = YacsaTheme.corners.medium - (YacsaTheme.corners.medium * Math.abs(topAppBarState.collapsedFraction))
    val systemUiController = rememberSystemUiController()

    when {
        uiState.isLoading -> {
            systemUiController.setNavigationBarColor(
                color = YacsaTheme.colors.surface,
            )
            ContentIsLoading()
        }

        uiState.isError -> {
            ContentError(
                errorMessage = UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).asString()
            ){

            }
        }

        else -> {
            systemUiController.setNavigationBarColor(
                color =YacsaTheme.colors.surface,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(YacsaTheme.colors.background),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = corner, topEnd = corner))
                        .background(YacsaTheme.colors.surface),
                ) {

                    if (!favouriteFlow?.value.isNullOrEmpty()) {
                        LazyColumn(
                            state = lazyListState,
                            verticalArrangement = Arrangement.spacedBy(YacsaTheme.spacing.small),
                            contentPadding = PaddingValues(
                                start = YacsaTheme.spacing.medium,
                                bottom = YacsaTheme.spacing.medium,
                                end = YacsaTheme.spacing.medium,
                                top = YacsaTheme.spacing.small,
                            )
                        ) {
                            items(items = favouriteFlow?.value!!) { item ->
                                ItemFetchedList(
                                    book = item!!,
                                    onItemContentClick = { /*TODO*/ },
                                    onFavouriteMark = onFavouriteMark
                                )
                            }
                        }
                    } else {
                        ContentNoData(
                            message = UiText.StringResource(dev.yacsa.localization.R.string.errors_no_data).asString()
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentFetched_Light() {
    YacsaTheme(false) {
//        ContentFetched(
//            foo = null
//        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentFetched_Dark() {
    YacsaTheme(true) {
//        ContentFetched(
//            foo = null
//        )
    }
}

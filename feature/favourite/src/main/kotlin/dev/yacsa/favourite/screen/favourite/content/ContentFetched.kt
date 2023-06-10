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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yacsa.favourite.screen.favourite.FavouriteUiState
import dev.yacsa.favourite.screen.favourite.item.ItemFetchedList
import dev.yacsa.model.model.BookUiModel
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

    val corner = 16.dp - (16.dp * Math.abs(topAppBarState.collapsedFraction))
    val systemUiController = rememberSystemUiController()
    val shape = RoundedCornerShape(16.dp)

    when {
        uiState.isLoading -> {
            systemUiController.setNavigationBarColor(
                color = YacsaTheme.colors.surface,
            )
            ContentIsLoading()
        }

        uiState.isError -> {
            systemUiController.setNavigationBarColor(
                color = Color(0xFFE0DFFD),
            )
            ContentError(
                errorMessage = "Moshi moshi?"
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
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(
                                start = 16.dp,
                                bottom = 16.dp,
                                end = 16.dp,
                                top = 8.dp,
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
                            message = "Nothing to show"
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentFetched() {
    YacsaTheme {
//        ContentFetched(
//            foo = null
//        )
    }
}

package dev.yacsa.favourite.screen.favourite.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    foo: TopAppBarState,
    state: LazyListState,
    onFavouriteMark: (Int, Boolean) -> Unit
) {
//    if (!foo?.value.isNullOrEmpty())
//        LazyColumn {
//            items(items = foo?.value!!) { item ->
//                Text(item?.title ?: "SWW")
//            }
//        } else {
//        Text("moshi moshi?")
//    }

    val corner = 16.dp - (16.dp * Math.abs(foo.collapsedFraction))
    val systemUiController = rememberSystemUiController()
    val shape = RoundedCornerShape(16.dp)

    when {
        uiState.isLoading -> {
            systemUiController.setNavigationBarColor(
                color = YacsaTheme.colors.statusBarColor,
            )
            ContentIsLoading()
        }

        uiState.isError -> {
            systemUiController.setNavigationBarColor(
                color = Color(0xFFE0DFFD),
            )
            ContentError(
                errorMessage = "Moshi moshi?"
            )
        }

        else -> {
            systemUiController.setNavigationBarColor(
                color = Color(0xFFE0DFFD),
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = corner, topEnd = corner))
                        .background(Color(0xFFE0DFFD)),
                ) {

                    if (!favouriteFlow?.value.isNullOrEmpty()) {
                        LazyColumn(
                            state = state,
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(
                                start = 16.dp,
                                bottom = 16.dp,
                                end = 16.dp,
                                top = 8.dp,
                            )
                        ) {
                            items(items = favouriteFlow?.value!!) { item ->
                                val dismissState =
                                    rememberDismissState(
                                        initialValue = DismissValue.Default,
                                    )

                                when {
                                    dismissState.isDismissed(DismissDirection.EndToStart) -> {
                                        item?.id?.let{
                                            onFavouriteMark(it,false)
                                        }
                                    }

                                    dismissState.isDismissed(DismissDirection.StartToEnd) -> {

                                    }
                                }

                                SwipeToDismiss(
                                    state = dismissState,
                                    /***  create dismiss alert Background */
                                    background = {
                                        val color = when (dismissState.dismissDirection) {
                                            DismissDirection.StartToEnd -> Color.Green
                                            DismissDirection.EndToStart -> Color.Red
                                            null -> Color.Transparent
                                        }
                                        val direction = dismissState.dismissDirection

                                        if (direction == DismissDirection.StartToEnd) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(8.dp)

                                            ) {
                                                Column(modifier = Modifier.align(Alignment.CenterStart)) {
                                                    Icon(
                                                        imageVector = Icons.Default.ArrowForward,
                                                        contentDescription = null,
                                                        tint = Color.White,
                                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                                    )
                                                    Text(
                                                        text = "Move to Archive",
                                                        fontWeight = FontWeight.Bold,
                                                        textAlign = TextAlign.Center,
                                                        color = Color.White
                                                    )
                                                }

                                            }
                                        } else {

                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(8.dp)

                                            ) {
                                                Column(modifier = Modifier.align(Alignment.CenterStart)) {
                                                    Icon(
                                                        imageVector = Icons.Default.ArrowForward,
                                                        contentDescription = null,
                                                        tint = Color.White,
                                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                                    )
                                                    Text(
                                                        text = "Move to Archive",
                                                        fontWeight = FontWeight.Bold,
                                                        textAlign = TextAlign.Center,
                                                        color = Color.White
                                                    )
                                                }
                                            }
                                        }
                                    },
                                    /**** Dismiss Content */
                                    dismissContent = {
                                        ItemFetchedList(
                                            book = item!!,
                                            onItemContentClick = { /*TODO*/ },
                                            onFavouriteMark = onFavouriteMark
                                        )
                                    },
                                    /*** Set Direction to dismiss */
                                    directions = setOf(
                                        DismissDirection.EndToStart,
                                        DismissDirection.StartToEnd
                                    ),
                                    dismissThresholds = { FractionalThreshold(0.2f) }

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

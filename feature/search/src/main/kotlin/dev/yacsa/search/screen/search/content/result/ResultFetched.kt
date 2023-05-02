package dev.yacsa.search.screen.search.content.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.search.screen.search.item.ItemFetchedList
import dev.yacsa.ui.composable.divider.AnimatedDivider
import dev.yacsa.ui.theme.YacsaTheme


@Composable
fun ResultFetched(
    modifier: Modifier = Modifier,
    resultSearch: List<BookUiModel>,
    onBookClicked: (Int) -> Unit,
) {
    val state = rememberLazyListState()
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
    ) {
        AnimatedDivider(state = state)
        LazyColumn(
            state = state,

            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                bottom = 16.dp,
                end = 16.dp,
                top = 8.dp,
            )
        ) {
            items(items = resultSearch) { item ->
                ItemFetchedList(
                    book = item,
                    onItemContentClick = {
                        // TODO: fix
                        item.id?.let { onBookClicked(it) }

                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ResultFetched() {
    YacsaTheme {
        ResultFetched(
            resultSearch = emptyList(),
            onBookClicked = {}
        )
    }
}
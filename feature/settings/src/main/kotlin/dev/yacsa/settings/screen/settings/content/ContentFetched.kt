package dev.yacsa.settings.screen.settings.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.ui.theme.YacsaTheme


@Composable
fun ContentFetched(
    modifier: Modifier = Modifier.fillMaxSize(),
    innerPadding: PaddingValues,
    state: LazyListState
) {
    val list = mutableListOf<String>()
    repeat(100) {
        list.add(it.toString())
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        state = state
    ) {
        items(list) { item ->
            Text(
                text = "foo"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentFetched() {
    YacsaTheme {
        ContentFetched(
            innerPadding = PaddingValues(6.dp),
            state = rememberLazyListState()
        )
    }
}
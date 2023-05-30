package dev.yacsa.favourite.screen.favourite.content

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ContentFetched(
    foo: State<List<BookUiModel?>?>?
) {
    if (!foo?.value.isNullOrEmpty())
        LazyColumn {
            items(items = foo?.value!!) { item ->
                Text(item?.title ?: "SWW")
            }
        } else {
        Text("moshi moshi?")
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentFetched() {
    YacsaTheme {
        ContentFetched(
            foo = null
        )
    }
}

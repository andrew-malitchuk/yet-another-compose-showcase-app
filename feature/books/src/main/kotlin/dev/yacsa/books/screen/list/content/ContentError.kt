package dev.yacsa.books.screen.list.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ContentError(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // TODO: fix
        Text(
            text = "some error"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentError() {
    YacsaTheme() {
        ContentError()
    }
}
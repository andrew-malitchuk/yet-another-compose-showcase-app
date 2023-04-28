package dev.yacsa.search.screen.search.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ContentFetched(
    modifier: Modifier= Modifier
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Search")
    }

}

@Composable
@Preview(showBackground = true)
fun Preview_ContentFetched(){
    YacsaTheme() {
        ContentFetched()
    }
}
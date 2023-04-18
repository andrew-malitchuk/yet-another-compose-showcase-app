package dev.yacsa.notfound.screen.notfound

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun NotFoundRoute(
    onBackClick: () -> Unit,
) {
    NotFoundScreen(onBackClick)
}

@Composable
fun NotFoundScreen(
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "404")
        OutlinedButton(onClick = { onBackClick() }) {
            Text("Go Back")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_NotFoundScreen() {
    YacsaTheme() {
        NotFoundScreen({})
    }
}

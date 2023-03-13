package dev.yacsa.books.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ListScreen(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "ListScreen",
                style = YacsaTheme.typography.heading
            )
            Button(
                onClick = {
                    onClick()
                },
            ) {
                Text(text = "GOTO: ", style = YacsaTheme.typography.caption)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListScreen() {
    YacsaTheme(useDarkTheme = true) {
        ListScreen(){}
    }
}
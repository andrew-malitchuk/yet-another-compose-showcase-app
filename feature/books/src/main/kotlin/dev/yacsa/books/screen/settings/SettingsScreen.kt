package dev.yacsa.books.screen.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun SettingsScreen(navHostController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Text(
                text = "SettingsScreen",
                style = YacsaTheme.typography.heading,
            )
            Button(
                onClick = { },
            ) {
                Text(text = "GOTO: ", style = YacsaTheme.typography.caption)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    YacsaTheme(useDarkTheme = true) {
        SettingsScreen(rememberNavController())
    }
}

package dev.yacsa.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun OnboardingScreen(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "OnboardingScreen",
                style = YacsaTheme.typography.heading
            )
            Button(
                onClick = {
                    onClick()
                }) {
                Text(text = "GOTO: ", style = YacsaTheme.typography.caption)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    YacsaTheme {
        OnboardingScreen(){}
    }
}
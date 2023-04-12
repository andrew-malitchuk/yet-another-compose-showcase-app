package dev.yacsa.featureflagmanager.screen.featureflagmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun FeatureFlagRoute(
    featureFlagViewModel: FeatureFlagViewModel = hiltViewModel(),
) {

    FeatureFlagScreen()
}

@Composable
fun FeatureFlagScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "FeatureFlagScreen")

    }
}

@Composable
@Preview(showBackground = true)
fun Preview_FeatureFlagScreen() {
    YacsaTheme() {
        FeatureFlagScreen()
    }
}
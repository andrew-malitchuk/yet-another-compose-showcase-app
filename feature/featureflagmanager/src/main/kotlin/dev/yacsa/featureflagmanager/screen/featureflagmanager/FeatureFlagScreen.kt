package dev.yacsa.featureflagmanager.screen.featureflagmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yacsa.featureflag.FeatureFlagModel
import dev.yacsa.featureflagmanager.screen.featureflagmanager.content.ContentError
import dev.yacsa.featureflagmanager.screen.featureflagmanager.content.ContentFetched
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun FeatureFlagRoute(
    featureFlagViewModel: FeatureFlagViewModel = hiltViewModel(),
) {

    val uiState by featureFlagViewModel.uiState.collectAsStateWithLifecycle()

    FeatureFlagScreen(
        uiState,
        isEnabled = {
            featureFlagViewModel.updateFeatureFlag(it)
        },
        isActive = {
            featureFlagViewModel.updateFeatureFlag(it)
        }
    )
}

@Composable
fun FeatureFlagScreen(
    uiState: FeatureFlagUiState,
    isEnabled: (FeatureFlagModel) -> Unit,
    isActive: (FeatureFlagModel) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    if (!uiState.isLoading && !uiState.isError) {
        systemUiController.setSystemBarsColor(
            color = YacsaTheme.colors.primaryText,
        )
        ContentFetched(
            uiState = uiState,
            isActive = isActive,
            isEnabled = isEnabled
        )
    } else {
        ContentError()
    }

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
        FeatureFlagScreen(
            FeatureFlagUiState(),
            {},
            {}
        )
    }
}
package dev.yacsa.featureflagmanager.screen.featureflagmanager.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.featureflag.FeatureFlagModel
import dev.yacsa.featureflagmanager.screen.featureflagmanager.FeatureFlagUiState
import dev.yacsa.featureflagmanager.screen.featureflagmanager.item.ItemFetched
import dev.yacsa.ui.theme.YacsaTheme
import logcat.logcat

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ContentFetched(
    modifier: Modifier = Modifier,
    uiState: FeatureFlagUiState,
    isEnabled: (FeatureFlagModel) -> Unit,
    isActive: (FeatureFlagModel) -> Unit
) {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text("I'm a TopAppBar")
            },
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxSize()
                // TODO: fix
                .padding(
                    horizontal = 16.dp,
                ),
        ) {
            items(items = uiState.featureFlags ?: emptyList()) { item ->
                ItemFetched(item = item, isEnabled = {
                    logcat { it.toString() }
                    isEnabled(item.also {
                        it.value = null
                    })
                }, isActive = { value ->
                    logcat { value.toString() }
                    isActive(item.also {
                        it.value = value
                    })
                })
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_ContentFetched() {
    YacsaTheme() {
        ContentFetched(uiState = FeatureFlagUiState(
            featureFlags = listOf(
                FeatureFlagModel("foo")
            ),
        ), isEnabled = {}, isActive = {})
    }
}
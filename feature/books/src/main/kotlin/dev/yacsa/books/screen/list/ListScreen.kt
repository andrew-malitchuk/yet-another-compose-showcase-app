package dev.yacsa.books.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.ui.theme.YacsaTheme
import logcat.logcat

@Composable
fun ListScreen(
    onClick: () -> Unit,
    listViewModel: ListViewModel = hiltViewModel()
) {
    val bar: StartUpConfigureDomainModel? by listViewModel.result.collectAsStateWithLifecycle()
    val error: Exception? by listViewModel.errorStateFlow.collectAsStateWithLifecycle()

    ListContent(bar, error, listViewModel)
}


@Composable
fun ListContent(
    foo: StartUpConfigureDomainModel?,
    error: Exception?,
    listViewModel: ListViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = error.toString(),
                style = YacsaTheme.typography.heading
            )
            Button(
                onClick = {
                    listViewModel.test()
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
//        ListScreen(){}
    }
}
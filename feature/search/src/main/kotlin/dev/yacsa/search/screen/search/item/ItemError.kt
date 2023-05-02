package dev.yacsa.search.screen.search.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ItemError(
    modifier: Modifier = Modifier,
    error: String,
    onRetry: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = modifier
                    .padding(16.dp),
                text = error,
                textAlign = TextAlign.Center,
                style = YacsaTheme.typography.caption,
            )
            OutlinedButton(
                modifier = modifier
                    .padding(16.dp),
                onClick = { onRetry() },
            ) {
                Text(text = "Retry")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ListErrorItem() {
    YacsaTheme {
        ItemError(
            error = "Lorem ipsum",
            onRetry = {},
        )
    }
}

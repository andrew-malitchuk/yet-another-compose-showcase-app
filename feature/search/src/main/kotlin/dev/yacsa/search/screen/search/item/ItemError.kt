package dev.yacsa.search.screen.search.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            .fillMaxWidth()
            .background(YacsaTheme.colors.surface),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "SWW",
                style = YacsaTheme.typography.header,
                color = YacsaTheme.colors.primary,
            )
            Spacer(
                modifier = Modifier.height(YacsaTheme.spacing.small),
            )
            Text(
                text = error,
                style = YacsaTheme.typography.title,
                color = YacsaTheme.colors.secondary,
            )
            OutlinedButton(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = YacsaTheme.colors.surface,
                    contentColor = YacsaTheme.colors.accent
                ),
                border = BorderStroke(1.dp, YacsaTheme.colors.accent),
                onClick = {
                    onRetry()
                }) {
                androidx.compose.material.Text(
                    text = "Okay",
                    style = YacsaTheme.typography.title,
                    color = YacsaTheme.colors.accent
                )
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

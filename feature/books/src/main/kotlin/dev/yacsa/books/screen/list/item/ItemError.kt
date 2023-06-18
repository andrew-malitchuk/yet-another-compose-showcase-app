package dev.yacsa.books.screen.list.item

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
import dev.yacsa.platform.string.UiText
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
                text = UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).asString(),
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
                    .padding(horizontal = YacsaTheme.spacing.medium),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = YacsaTheme.colors.surface,
                    contentColor = YacsaTheme.colors.accent
                ),
                border = BorderStroke(YacsaTheme.dividers.small, YacsaTheme.colors.accent),
                onClick = {
                    onRetry()
                }) {
                androidx.compose.material.Text(
                    text = UiText.StringResource(dev.yacsa.localization.R.string.general_ok).asString(),
                    style = YacsaTheme.typography.title,
                    color = YacsaTheme.colors.accent
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ListErrorItem_Light() {
    YacsaTheme(false) {
        ItemError(
            error = "Lorem ipsum",
            onRetry = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ListErrorItem_Dark() {
    YacsaTheme(true) {
        ItemError(
            error = "Lorem ipsum",
            onRetry = {},
        )
    }
}

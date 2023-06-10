package dev.yacsa.ui.composable.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun OfflineSnackbar(
    message: String
) {
    val shape = RoundedCornerShape(
        bottomEnd = YacsaTheme.corners.medium,
        bottomStart = YacsaTheme.corners.medium
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(YacsaTheme.colors.primary)
    ) {
        Text(
            modifier = Modifier
                .padding(YacsaTheme.spacing.small)
                .fillMaxWidth(),
            text = message,
            style = YacsaTheme.typography.title,
            color = YacsaTheme.colors.background,
            textAlign = TextAlign.Center
        )

    }
}


@Preview(showBackground = true)
@Composable
fun Preivew_OfflineSnackbar() {

    YacsaTheme() {
        OfflineSnackbar(
            message = "Foobar"
        )
    }
}
package dev.yacsa.search.screen.search.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ItemFetchedGrid(
    modifier: Modifier = Modifier,
    book: BookUiModel,
    onItemContentClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        val painter =
            rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = book.formats?.imageJpeg)
                    .placeholder(
                        R.drawable.app_icon_launcher_foreground,
                    )
                    .build(),
            )
        // TODO: fix
        Card(
            elevation = 0.dp,
            shape = RoundedCornerShape(YacsaTheme.corners.small),
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f / 1.5f),
            )
        }
        Spacer(modifier = modifier.height(YacsaTheme.spacing.small))
        Text(
            text = (book.authors?.firstOrNull()?.name ?: UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).asString()),
            style = YacsaTheme.typography.caption,
            textAlign = TextAlign.Center,
            maxLines = 1,
            minLines = 1,
        )
        Spacer(modifier = modifier.height(YacsaTheme.spacing.small))
        Text(
            text = (book.title ?: UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).asString()),
            style = YacsaTheme.typography.title,
            textAlign = TextAlign.Center,
            maxLines = 2,
            minLines = 2,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ItemFetchedGrid_Light() {
    YacsaTheme(false) {
        ItemFetchedGrid(
            book = BookUiModel(1, "foobar", null, emptyList(), emptyList(), emptyList(), emptyList(), true, null, null, 10, true),
            onItemContentClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ItemFetchedGrid_Dark() {
    YacsaTheme(true) {
        ItemFetchedGrid(
            book = BookUiModel(1, "foobar", null, emptyList(), emptyList(), emptyList(), emptyList(), true, null, null, 10, true),
            onItemContentClick = {},
        )
    }
}

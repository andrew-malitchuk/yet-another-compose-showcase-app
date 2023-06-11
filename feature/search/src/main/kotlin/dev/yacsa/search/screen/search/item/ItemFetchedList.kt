package dev.yacsa.search.screen.search.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ItemFetchedList(
    modifier: Modifier = Modifier,
    book: BookUiModel,
    onItemContentClick: () -> Unit,
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


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemContentClick()
            },
        contentColor = YacsaTheme.colors.background,
        backgroundColor = YacsaTheme.colors.background,
        shape = YacsaTheme.shapes.cornersStyle,
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(YacsaTheme.spacing.small)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                border = BorderStroke(YacsaTheme.dividers.small, YacsaTheme.colors.primary),
                shape = YacsaTheme.shapes.cornersStyle,
                elevation = 0.dp,
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .height(128.dp)
                        .aspectRatio(1f / 1.5f),
                )
            }
            Spacer(modifier = modifier.width(YacsaTheme.spacing.medium))
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
            ) {
                Spacer(modifier = modifier.height(YacsaTheme.spacing.small))
                Text(
                    // TODO: fix
                    text = (book.authors?.firstOrNull()?.name ?: "NI"),
                    style = YacsaTheme.typography.caption,
                    color = YacsaTheme.colors.primary,
                    maxLines = 1,
                    minLines = 1,
                )
                Spacer(modifier = modifier.height(YacsaTheme.spacing.small))
                Text(
                    // TODO: fix
                    text = (book.title ?: "NI"),
                    style = YacsaTheme.typography.title,
                    color = YacsaTheme.colors.secondary,
                    maxLines = 2,
                    minLines = 2,
                )
                Spacer(modifier = modifier.height(YacsaTheme.spacing.small))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_archive_box_regular_16),
                        contentDescription = null,
                        tint = YacsaTheme.colors.accent
                    )
                    Spacer(modifier = modifier.width(YacsaTheme.spacing.extraSmall))
                    Text(
                        // TODO: fix
                        text = book.downloadCount?.toString() ?: "NI",
                        style = YacsaTheme.typography.title,
                        color = YacsaTheme.colors.secondary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ListItem_Dark() {
    YacsaTheme(true) {
        ItemFetchedList(
            book = BookUiModel(
                1,
                "foobar",
                null,
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                true,
                null,
                null,
                10,
                true
            ),
            onItemContentClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ItemFetchedList_Light() {
    YacsaTheme(false) {
        ItemFetchedList(
            book = BookUiModel(
                1,
                "foobar",
                null,
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                true,
                null,
                null,
                10,
                true
            ),
            onItemContentClick = {},
        )
    }
}

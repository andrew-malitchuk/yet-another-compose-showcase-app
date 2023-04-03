package dev.yacsa.books.screen.list.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ItemFetched(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String?,
    description: String,
    onItemContentClick: ()->Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onItemContentClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter =
            rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = imageUrl)
                    .apply(block = fun ImageRequest.Builder.() {
                        transformations(
                            CircleCropTransformation()
                        )
                    }).build()
            )
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(36.dp),
        )
        Spacer(modifier = modifier.width(16.dp))
        Column(
            modifier = modifier.fillMaxWidth(),
        ) {
            Text(
                text = title,
                style = YacsaTheme.typography.heading,
                maxLines = 1,
                minLines = 1
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = description,
                style = YacsaTheme.typography.caption,
                maxLines = 1,
                minLines = 1
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun Preview_ListItem_Dark() {
    YacsaTheme(true) {
        ItemFetched(
            title = "title",
            description = "description",
            imageUrl = null,
            onItemContentClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ListItem_Light() {
    YacsaTheme(false) {
        ItemFetched(
            title = "title",
            description = "description",
            imageUrl = null,
            onItemContentClick = {}
        )
    }
}
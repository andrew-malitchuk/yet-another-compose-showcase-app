package dev.yacsa.books.screen.list.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import coil.transform.CircleCropTransformation
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ItemFetchedGrid(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String?,
    description: String,
    onItemContentClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
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
            modifier = Modifier.aspectRatio(1f)
        )
        Text(
            text = title,
            style = YacsaTheme.typography.heading,
            textAlign = TextAlign.Center,
            maxLines = 1,
            minLines = 1,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = description,
            style = YacsaTheme.typography.caption,
            textAlign = TextAlign.Center,
            maxLines = 1,
            minLines = 1,
            modifier = Modifier
                .padding(8.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun Preview_ItemFetchedGrid() {
    YacsaTheme {
        ItemFetchedGrid(
            title = "title",
            description = "description",
            imageUrl = null,
            onItemContentClick = {}
        )
    }
}
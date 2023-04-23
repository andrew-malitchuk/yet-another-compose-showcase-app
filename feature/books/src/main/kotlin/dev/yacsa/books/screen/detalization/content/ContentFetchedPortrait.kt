package dev.yacsa.books.screen.detalization.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import dev.yacsa.books.screen.detalization.composable.SubjectBlock
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ContentFetchedPortrait(
    modifier: Modifier = Modifier,
    book: BookUiModel?,
    onBackClick:()->Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier.wrapContentSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .aspectRatio(1f / 1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                val painter =
                    rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = book?.formats?.imageJpeg)
                            .placeholder(
                                R.drawable.ic_launcher_foreground,
                            )
                            .build(),
                    )

                Card(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .height(256.dp)
                            .aspectRatio(1f / 1.5f),
                    )
                }
                Spacer(modifier = modifier.height(6.dp))
                Text(
                    text = book?.title ?: "SWW",
                    style = YacsaTheme.typography.title,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = modifier.height(6.dp))
                Text(
                    text = book?.authors?.firstOrNull()?.name ?: "SWW",
                    style = YacsaTheme.typography.caption,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                )
            }

            IconButton(
                modifier = Modifier
                    .align(Alignment.TopStart),
                onClick = {
                    onBackClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = ""
                )
            }

        }
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
        ) {
            // TODO: fix
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(Color.DarkGray),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            ) {
                // TODO: fix
                Text(
                    text = "Subjects",
                    style = YacsaTheme.typography.title,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = modifier.height(6.dp))
                SubjectBlock(
                    subjects = book?.subjects?.filterNotNull() ?: emptyList()
                )
            }
        }
        if (!book?.bookshelves.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
            ) {
                // TODO: fix
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(Color.DarkGray),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                ) {
                    // TODO: fix
                    Text(
                        text = "Bookshelves",
                        style = YacsaTheme.typography.title,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = modifier.height(6.dp))
                    SubjectBlock(
                        subjects = book?.bookshelves?.filterNotNull() ?: emptyList()
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_ContentFetchedPortrait() {
    YacsaTheme {
        ContentFetchedPortrait(book = null, onBackClick = {})
    }
}

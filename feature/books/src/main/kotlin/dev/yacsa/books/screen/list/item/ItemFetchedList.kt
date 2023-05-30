package dev.yacsa.books.screen.list.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
//                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                )
                .build(),
        )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemContentClick()
            },
        border = BorderStroke(1.dp, Color(0xFF7766C6)),
        shape = RoundedCornerShape(16.dp),
        elevation = 10.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                elevation = 4.dp,
                border = BorderStroke(1.dp, Color(0xFF46467A)),
                shape = RoundedCornerShape(16.dp),
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .height(128.dp)
                        .aspectRatio(1f / 1.5f),
                )
            }
            Spacer(modifier = modifier.width(16.dp))
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
            ) {
                Spacer(modifier = modifier.height(6.dp))
                Text(
                    text = (book.authors?.firstOrNull()?.name ?: "NI"),
                    style = YacsaTheme.typography.caption,
                    maxLines = 1,
                    minLines = 1,
                )
                Spacer(modifier = modifier.height(6.dp))
                Text(
                    text = (book.title ?: "NI"),
                    style = YacsaTheme.typography.title,
                    maxLines = 2,
                    minLines = 2,
                )
                Spacer(modifier = modifier.height(6.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_archive_box_regular_16),
                        contentDescription = null,
                    )
                    Spacer(modifier = modifier.width(4.dp))
                    Text(
                        text = book.downloadCount?.toString() ?: "NI",
                        style = YacsaTheme.typography.caption,
                    )
//                    if (book.isFavourite == true) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.icon_heart_fill_24),
//                            contentDescription = null
//                        )
//                    } else {
//                        Icon(
//                            painter = painterResource(id = R.drawable.icon_heart_regulat_24),
//                            contentDescription = null
//                        )
//                    }
                }
            }
        }
    }

//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .clickable {
//                onItemContentClick()
//            },
//    ) {
//        val painter =
//            rememberAsyncImagePainter(
//                ImageRequest.Builder(LocalContext.current)
//                    .data(data = book.formats?.imageJpeg)
//                    .placeholder(
//                        R.drawable.ic_launcher_foreground,
//                    )
//                    .build(),
//            )
//        // TODO: fix
//        Card(
//            elevation = 4.dp,
//            shape = RoundedCornerShape(8.dp),
//        ) {
//            Image(
//                painter = painter,
//                contentDescription = null,
//                modifier = Modifier
//                    .height(128.dp)
//                    .aspectRatio(1f / 1.5f),
//            )
//        }
//        Spacer(modifier = modifier.width(16.dp))
//        Column(
//            modifier = modifier
//                .fillMaxWidth(),
//            verticalArrangement = Arrangement.Top,
//        ) {
//            Spacer(modifier = modifier.height(6.dp))
//            Text(
//                text = (book.authors?.firstOrNull()?.name ?: "NI"),
//                style = YacsaTheme.typography.caption,
//                maxLines = 1,
//                minLines = 1,
//            )
//            Spacer(modifier = modifier.height(6.dp))
//            Text(
//                text = (book.title ?: "NI"),
//                style = YacsaTheme.typography.title,
//                maxLines = 2,
//                minLines = 2,
//            )
//            Spacer(modifier = modifier.height(6.dp))
//            Row(
//                modifier = modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//            ) {
//                Icon(imageVector = Icons.Outlined.ArrowDropDown, contentDescription = "")
//                Spacer(modifier = modifier.width(4.dp))
//                Text(
//                    text = book.downloadCount?.toString() ?: "NI",
//                    style = YacsaTheme.typography.caption,
//                )
//            }
//        }
//    }
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

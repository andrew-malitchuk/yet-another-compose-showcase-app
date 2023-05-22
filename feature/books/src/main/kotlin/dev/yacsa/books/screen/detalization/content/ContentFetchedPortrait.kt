package dev.yacsa.books.screen.detalization.content

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.books.screen.detalization.composable.toolbar.Bar
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.theme.YacsaTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ContentFetchedPortrait(
    modifier: Modifier = Modifier,
    book: BookUiModel?,
    onBackClick: () -> Unit,
) {

    Bar(book)


   /* Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier.wrapContentSize(),
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
                Row() {
                    Text(
                        text = book?.title ?: "SWW",
                        style = YacsaTheme.typography.title,
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                    )
                    if (book?.copyright == true) {
                        Text(text = "©")
                    }
                }
                Spacer(modifier = modifier.height(6.dp))
                Text(
                    text = book?.authors?.firstOrNull()?.name ?: "SWW",
                    style = YacsaTheme.typography.caption,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = modifier.height(6.dp))
                Row() {
                    Icon(imageVector = Icons.Outlined.ArrowDropDown, contentDescription = "")
                    Spacer(modifier = modifier.width(4.dp))
                    Text(
                        text = book?.downloadCount?.toString() ?: "NI",
                        style = YacsaTheme.typography.caption,
                    )
                }
            }

            IconButton(
                modifier = Modifier
                    .align(Alignment.TopStart),
                onClick = {
                    onBackClick()
                },
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "",
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
                    subjects = book?.subjects?.filterNotNull() ?: emptyList(),
                )
            }
        }
        if (!book?.languages.isNullOrEmpty()) {
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
                        text = "Languages",
                        style = YacsaTheme.typography.title,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = modifier.height(6.dp))
                    SubjectBlock(
                        subjects = book?.languages?.filterNotNull() ?: emptyList(),
                    )
                }
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
                        subjects = book?.bookshelves?.filterNotNull() ?: emptyList(),
                    )
                }
            }
        }
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
                    text = "Authors",
                    style = YacsaTheme.typography.title,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = modifier.height(6.dp))
                PersonBlock(
                    authors = book?.authors?.filterNotNull() ?: emptyList(),
                )
            }
        }
        if (!book?.translators.isNullOrEmpty()) {
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
                        text = "Translators",
                        style = YacsaTheme.typography.title,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = modifier.height(6.dp))
                    PersonBlock(
                        authors = book?.translators?.filterNotNull() ?: emptyList(),
                    )
                }
            }
        }
    }*/
}

@Composable
@Preview(showBackground = true)
fun Preview_ContentFetchedPortrait() {
    YacsaTheme {
        ContentFetchedPortrait(book = null, onBackClick = {})
    }
}

package dev.yacsa.books.screen.detalization.composable.toolbar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import dev.yacsa.books.R
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.theme.YacsaTheme

@OptIn(ExperimentalMotionApi::class, ExperimentalFoundationApi::class)
@Composable
fun CollapsingToolbar(
    lazyScrollState: LazyListState,
    book: BookUiModel,
    onBackClick:()->Unit,
    onDownloadClick:()->Unit
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.collapse_toolbar).readBytes().decodeToString()
    }

    val progress by animateFloatAsState(
//        targetValue = if (lazyScrollState.firstVisibleItemIndex in 0..1) 0f else 1f,
//        targetValue = if (!lazyScrollState.canScrollBackward) 0f else 1f,
        targetValue = if (!lazyScrollState.canScrollBackward && !lazyScrollState.isScrollInProgress) 0f else 1f,
        tween(500)
    )
    val motionHeight by animateDpAsState(
//        targetValue = if (lazyScrollState.firstVisibleItemIndex in 0..1) 288.dp else 64.dp,
//        targetValue = if (!lazyScrollState.canScrollBackward) 288.dp else 64.dp,
        targetValue = if (!lazyScrollState.canScrollBackward && !lazyScrollState.isScrollInProgress) 288.dp else 64.dp,
        tween(500)
    )
    val corner = 16.dp - (16.dp * Math.abs(progress))

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .height(motionHeight)
    ) {
        val boxProperties = customProperties(id = "box")

        val painter =
            rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = book.formats?.imageJpeg)
                    .placeholder(
                        dev.yacsa.ui.R.drawable.ic_launcher_foreground,
                    )
                    .build(),
            )

        Box(
            modifier = Modifier
                .layoutId("box")
                .clip(RoundedCornerShape(bottomStart = corner, bottomEnd = corner))
                .background(Color(0xFFE0DFFD))
        )
        SmallFloatingActionButton(
            modifier = Modifier
                .layoutId("back"),
            onClick = {
                onBackClick()
            }
        ) {
            Icon(
                painter = painterResource(id = dev.yacsa.ui.R.drawable.icon_caret_left_regular_24),
                contentDescription = null,
            )
        }

        Card(
            modifier = Modifier.layoutId("img"),
            elevation = 4.dp,
            border = BorderStroke(1.dp, Color(0xFF46467A)),
            shape = RoundedCornerShape(16.dp),
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .height(256.dp)
                    .aspectRatio(1f / 1.5f),
            )
        }

        Text(
            modifier = Modifier.layoutId("title").basicMarquee(),
            text = book.title ?: "SWW",
            style = YacsaTheme.typography.heading,
            maxLines = 2,
            textAlign = TextAlign.Start,
        )

        Text(
            modifier = Modifier.layoutId("author"),
            text = book?.authors?.firstOrNull()?.name ?: "SWW",
            style = YacsaTheme.typography.title,
            maxLines = 2,
            minLines = 2,
            textAlign = TextAlign.Start,
        )

        Row(
            modifier = Modifier.layoutId("download"),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = dev.yacsa.ui.R.drawable.icon_archive_box_regular_16),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = book.downloadCount?.toString() ?: "NI",
                style = YacsaTheme.typography.caption,
            )
        }

        LazyRow(
            modifier = Modifier.layoutId("actions")
        ) {
            item {
                SmallFloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = dev.yacsa.ui.R.drawable.icon_heart_regulat_24),
                        contentDescription = null,
                    )
                }
            }
            item {
                SmallFloatingActionButton(onClick = { onDownloadClick()}) {
                    Icon(
                        painter = painterResource(id = dev.yacsa.ui.R.drawable.icon_download_regular_24),
                        contentDescription = null,
                    )
                }
            }
            item {
                SmallFloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = dev.yacsa.ui.R.drawable.icon_share_regular_24),
                        contentDescription = null,
                    )
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun Preview_CollapsingToolbar() {
    YacsaTheme {
        CollapsingToolbar(
            lazyScrollState = rememberLazyListState(),
            book = BookUiModel(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
            ),
            onBackClick={},
            onDownloadClick={}
        )
    }
}
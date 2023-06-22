package dev.yacsa.books.screen.detalization.composable.toolbar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.composable.button.TwoStateButton
import dev.yacsa.ui.theme.YacsaTheme

@OptIn(ExperimentalMotionApi::class, ExperimentalFoundationApi::class)
@Composable
fun CollapsingToolbar(
    lazyScrollState: LazyListState,
    book: BookUiModel,
    onBackClick: () -> Unit,
    onDownloadClick: () -> Unit,
    favourite: MutableState<Boolean?>,
    onShareClick: (Int) -> Unit,
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.collapse_toolbar).readBytes().decodeToString()
    }

    val progress by animateFloatAsState(
        targetValue = if (!lazyScrollState.canScrollBackward && !lazyScrollState.isScrollInProgress) 0f else 1f,
        tween(500),
    )
    val motionHeight by animateDpAsState(
        targetValue = if (!lazyScrollState.canScrollBackward && !lazyScrollState.isScrollInProgress) 288.dp else 64.dp,
        tween(500),
    )
    val corner = YacsaTheme.corners.medium - (YacsaTheme.corners.medium * Math.abs(progress))

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .background(YacsaTheme.colors.background)
            .height(motionHeight),
    ) {
        val painter =
            rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = book.formats?.imageJpeg)
                    .placeholder(
                        dev.yacsa.ui.R.drawable.app_icon_launcher_foreground,
                    )
                    .build(),
            )

        Box(
            modifier = Modifier
                .layoutId("box")
                .clip(RoundedCornerShape(bottomStart = corner, bottomEnd = corner))
                .background(YacsaTheme.colors.surface),
        )
        SmallFloatingActionButton(
            modifier = Modifier
                .layoutId("back"),
            onClick = {
                onBackClick()
            },
            containerColor = YacsaTheme.colors.accent,
            elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),
        ) {
            Icon(
                painter = painterResource(id = dev.yacsa.ui.R.drawable.icon_caret_left_regular_24),
                contentDescription = null,
                tint = YacsaTheme.colors.primary,
            )
        }

        Card(
            modifier = Modifier.layoutId("img"),
            elevation = 0.dp,
            border = BorderStroke(YacsaTheme.dividers.small, YacsaTheme.colors.primary),
            shape = RoundedCornerShape(YacsaTheme.corners.medium),
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
            modifier = Modifier
                .layoutId("title")
                .basicMarquee(),
            text = book.title ?: UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).asString(),
            style = YacsaTheme.typography.caption,
            color = YacsaTheme.colors.primary,
            maxLines = 2,
            textAlign = TextAlign.Start,
        )

        Text(
            modifier = Modifier.layoutId("author"),
            text = book?.authors?.firstOrNull()?.name ?: UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).asString(),
            style = YacsaTheme.typography.title,
            color = YacsaTheme.colors.secondary,
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
                tint = YacsaTheme.colors.accent,
            )
            Spacer(modifier = Modifier.width(YacsaTheme.spacing.extraSmall))
            Text(
                text = book.downloadCount?.toString() ?: UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).asString(),
                style = YacsaTheme.typography.title,
                color = YacsaTheme.colors.secondary,
            )
        }

        LazyRow(
            modifier = Modifier
                .layoutId("actions"),
            horizontalArrangement = Arrangement.spacedBy(YacsaTheme.spacing.small),
            contentPadding = PaddingValues(horizontal = 1.dp),
        ) {
            item {
                TwoStateButton(
                    checkedState = favourite,
                    defaultIcon = dev.yacsa.ui.R.drawable.icon_heart_regulat_24,
                    selectedIcon = dev.yacsa.ui.R.drawable.icon_heart_fill_24,
                )
            }
            item {
                SmallFloatingActionButton(
                    onClick = { onDownloadClick() },
                    containerColor = YacsaTheme.colors.primary,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),

                ) {
                    Box(
                        modifier = Modifier.padding(12.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = dev.yacsa.ui.R.drawable.icon_download_regular_24),
                            contentDescription = null,
                            tint = YacsaTheme.colors.accent,
                        )
                    }
                }
            }
            item {
                SmallFloatingActionButton(
                    onClick = { book.id?.let { onShareClick(it) } },
                    containerColor = YacsaTheme.colors.primary,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),
                ) {
                    Box(
                        modifier = Modifier.padding(12.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = dev.yacsa.ui.R.drawable.icon_share_regular_24),
                            contentDescription = null,
                            tint = YacsaTheme.colors.accent,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_CollapsingToolbar_Light() {
    YacsaTheme(false) {
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
                true,
            ),
            onBackClick = {},
            onDownloadClick = {},
            favourite = remember { mutableStateOf(false) },
            onShareClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_CollapsingToolbar_Dark() {
    YacsaTheme(true) {
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
                true,
            ),
            onBackClick = {},
            onDownloadClick = {},
            favourite = remember { mutableStateOf(false) },
            onShareClick = {},
        )
    }
}

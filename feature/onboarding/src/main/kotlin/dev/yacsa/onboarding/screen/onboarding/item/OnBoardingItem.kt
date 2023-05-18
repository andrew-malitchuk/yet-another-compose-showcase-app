package dev.yacsa.onboarding.screen.onboarding.item

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingItem(
    @DrawableRes imageId: Int,
    header: String,
    caption: String,
    state: PagerState,
) {
    val position = state.currentPageOffset
    val scaleMultiplier = 0.25
    val minScale = 0.75
    val scaleValue = if (position < 0) {
        (((minScale - (-position) * scaleMultiplier).toFloat() + scaleMultiplier).toFloat())
    } else if (position > 0 && position <= 1) {
        (((minScale - position * scaleMultiplier).toFloat() + scaleMultiplier).toFloat())
    } else {
        1f
    }
    val alphaValue = if (position < 0) {
        (1 - kotlin.math.abs(position))
    } else if (position > 0 && position <= 1) {
        (1 - position)
    } else {
        1f
    }

    val cornerValue = if (position < 0) {
        (1 - kotlin.math.abs(position))
    } else if (position > 0 && position <= 1) {
        (1 - position)
    } else {
        1f
    }

    val corner = 64.dp - (64.dp * Math.abs(cornerValue))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .scale(scaleValue)
            .clip(RoundedCornerShape(size = corner))
            .background(Color(0xFFE0DFFD)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .padding(
                        start = 50.dp,
                        end = 50.dp,
                    )
                    .scale(scaleValue)
                    .alpha(alphaValue),
            )
            Spacer(
                modifier = Modifier.height(26.dp),
            )
            Text(
                text = header,
                style = YacsaTheme.typography.heading,
                modifier = Modifier.alpha(alphaValue),
            )
            Spacer(
                modifier = Modifier.height(8.dp),
            )
            Text(
                text = caption,
                style = YacsaTheme.typography.caption,
                modifier = Modifier.alpha(alphaValue),
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun Preview_OnboardingItem() {
    YacsaTheme {
        OnboardingItem(
            imageId = R.drawable.illustration_mobile_interface,
            header = "foo",
            caption = "bar",
            state = rememberPagerState(),
        )
    }
}

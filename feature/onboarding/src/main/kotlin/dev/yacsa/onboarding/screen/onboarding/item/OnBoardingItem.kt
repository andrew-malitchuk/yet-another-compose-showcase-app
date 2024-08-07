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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme
import io.github.serpro69.kfaker.Faker

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

    val corner = YacsaTheme.corners.extraLarge - (YacsaTheme.corners.extraLarge * Math.abs(cornerValue))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .scale(scaleValue)
            .clip(RoundedCornerShape(size = corner))
            .background(YacsaTheme.colors.surface),
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
                        start = YacsaTheme.spacing.extraLarge,
                        end = YacsaTheme.spacing.extraLarge,
                    )
                    .scale(scaleValue)
                    .alpha(alphaValue),
            )
            Spacer(
                modifier = Modifier.height(YacsaTheme.spacing.default),
            )
            Text(
                text = header,
                style = YacsaTheme.typography.header,
                color = YacsaTheme.colors.primary,
                modifier = Modifier.alpha(alphaValue),
            )
            Spacer(
                modifier = Modifier.height(YacsaTheme.spacing.small),
            )
            Text(
                text = caption,
                style = YacsaTheme.typography.title,
                color = YacsaTheme.colors.secondary,
                modifier = Modifier.alpha(alphaValue),
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun Preview_OnboardingItem_Light() {
    val faker = Faker()
    YacsaTheme(false) {
        OnboardingItem(
            imageId = R.drawable.illustration_mobile_interface,
            header = faker.quote.fortuneCookie(),
            caption = faker.quote.fortuneCookie(),
            state = rememberPagerState(),
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun Preview_OnboardingItem_Dark() {
    val faker = Faker()
    YacsaTheme(true) {
        OnboardingItem(
            imageId = R.drawable.illustration_mobile_interface,
            header = faker.quote.fortuneCookie(),
            caption = faker.quote.fortuneCookie(),
            state = rememberPagerState(),
        )
    }
}

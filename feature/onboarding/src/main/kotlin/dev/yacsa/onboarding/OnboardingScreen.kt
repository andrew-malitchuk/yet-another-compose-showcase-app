package dev.yacsa.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import dev.yacsa.ui.theme.YacsaTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    onClick: () -> Unit
) {

    val state = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopSection(
            onBackClick = {

            },
            onSkipClick = {

            }
        )
        HorizontalPager(
            count = 3,
            state = state,
            modifier = Modifier
                .weight(1f, true)
                .background(Color.Cyan)
        ) {
            OnBoardingItem(
                dev.yacsa.ui.R.drawable.ic_launcher_foreground,
                "foo",
                "bar"
            )
        }
        BottomSection(state)
    }
}

@Composable
fun TopSection(
    onBackClick: () -> Unit = {},
    onSkipClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = null,
                tint = YacsaTheme.colors.primaryText
            )
        }
        TextButton(
            onClick = onSkipClick,
            modifier = Modifier.align(Alignment.CenterEnd),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                // TODO: move
                text = "Skip",
                color = YacsaTheme.colors.primaryText
            )
        }
    }
}

@Composable
fun OnBoardingItem(
    @DrawableRes imageId: Int,
    header: String,
    caption: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier.padding(
                start = 50.dp,
                end = 50.dp,
            )
        )
        Spacer(
            modifier = Modifier.height(26.dp)
        )
        Text(
            text = header,
            style = YacsaTheme.typography.heading
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text(
            text = caption,
            style = YacsaTheme.typography.caption
        )
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomSection(state: PagerState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalPagerIndicator(
            pagerState = state,
            activeColor = Color.Black,
            inactiveColor = Color(0xFF00BB00),
            indicatorShape = CutCornerShape(size = 4.dp),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen_Dark() {
    YacsaTheme(useDarkTheme = true) {
        OnboardingScreen() {}
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewOnboardingScreen_Light() {
//    YacsaTheme(useDarkTheme = false) {
//        OnboardingScreen() {}
//    }
//}
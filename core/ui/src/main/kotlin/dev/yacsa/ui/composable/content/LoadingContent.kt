package dev.yacsa.ui.composable.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ContentIsLoading(
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("lottie_loading_accent.json"))
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setSystemBarsColor(
            color = YacsaTheme.colors.background,
        )
        setNavigationBarColor(
            color = YacsaTheme.colors.background,
        )
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(YacsaTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        LottieAnimation(
            modifier=Modifier.height(256.dp).width(256.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentIsLoading() {
    YacsaTheme(true) {
        ContentIsLoading()
    }
}

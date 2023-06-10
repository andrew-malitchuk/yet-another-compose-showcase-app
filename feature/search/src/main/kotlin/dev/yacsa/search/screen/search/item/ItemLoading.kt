package dev.yacsa.search.screen.search.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ItemLoading(
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("lottie_loading_accent.json"))

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(YacsaTheme.colors.surface),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LottieAnimation(
            modifier=Modifier.height(128.dp).width(128.dp).padding(16.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ListLoadingItem_Dark() {
    YacsaTheme(true) {
        ItemLoading()
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ListLoadingItem_Light() {
    YacsaTheme(false) {
        ItemLoading()
    }
}


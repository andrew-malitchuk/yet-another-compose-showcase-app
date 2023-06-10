package dev.yacsa.notfound.screen.notfound

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun NotFoundRoute(
    onBackClick: () -> Unit,
) {
    NotFoundScreen(onBackClick)
}

@Composable
fun NotFoundScreen(
    onBackClick: () -> Unit,
) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(YacsaTheme.spacing.medium),

        ) {
        SmallFloatingActionButton(onClick = { onBackClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_caret_left_regular_24),
                contentDescription = null
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(size = YacsaTheme.corners.medium))
                .align(Alignment.Center)
                    // TODO: fix
                .background(Color(0xFFE0DFFD)),
            contentAlignment = Alignment.Center

        ) {
            Image(
                modifier = Modifier
                    .padding(YacsaTheme.spacing.medium),
                painter = painterResource(id = R.drawable.illustration_taken),
                contentDescription = null,
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun Preview_NotFoundScreen() {
    YacsaTheme() {
        NotFoundScreen({})
    }
}

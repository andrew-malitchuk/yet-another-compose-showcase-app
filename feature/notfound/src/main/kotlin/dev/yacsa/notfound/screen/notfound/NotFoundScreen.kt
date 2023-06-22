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
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            .background(YacsaTheme.colors.background)
            .padding(YacsaTheme.spacing.medium),

    ) {
        SmallFloatingActionButton(
            onClick = { onBackClick() },
            elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),
            containerColor = YacsaTheme.colors.accent,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_caret_left_regular_24),
                contentDescription = null,
                tint = YacsaTheme.colors.primary,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(size = YacsaTheme.corners.medium))
                .align(Alignment.Center)
                .background(YacsaTheme.colors.surface),
            contentAlignment = Alignment.Center,

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
fun Preview_NotFoundScreen_Light() {
    YacsaTheme(false) {
        NotFoundScreen({})
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_NotFoundScreen_Dark() {
    YacsaTheme(true) {
        NotFoundScreen({})
    }
}

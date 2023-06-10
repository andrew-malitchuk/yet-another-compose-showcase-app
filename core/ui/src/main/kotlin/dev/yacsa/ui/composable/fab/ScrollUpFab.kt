package dev.yacsa.ui.composable.fab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ScrollUpFab(
    modifier: Modifier,
    isVisibleBecauseOfScrolling: Boolean,
    onClick: () -> Unit,
) {
    val density = LocalDensity.current
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisibleBecauseOfScrolling,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 250,
            ),
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = 250,
            ),
        ),

        ) {
        FloatingActionButton(
            onClick = { onClick() },
            backgroundColor = YacsaTheme.colors.accent,
            elevation = FloatingActionButtonDefaults.elevation(0.dp,0.dp,0.dp,0.dp)
        ) {
            androidx.compose.material3.Icon(
                painter = painterResource(id = R.drawable.icon_arrow_up_regular_24),
                contentDescription = null,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ScrollUpFab() {
    YacsaTheme {
        ScrollUpFab(
            modifier = Modifier,
            isVisibleBecauseOfScrolling = true,
        ) {}
    }
}

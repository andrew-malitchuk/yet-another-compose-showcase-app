package dev.yacsa.ui.composable.fab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        enter = slideInHorizontally {
            with(density) { 16.dp.roundToPx() }
        },
        exit = slideOutHorizontally {
            with(density) { 16.dp.roundToPx() }
        },
    ) {
        FloatingActionButton(
            onClick = { onClick() },
        ) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Outlined.KeyboardArrowUp,
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

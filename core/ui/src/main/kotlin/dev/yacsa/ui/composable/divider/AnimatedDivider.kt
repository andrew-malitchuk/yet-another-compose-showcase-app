package dev.yacsa.ui.composable.divider

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun AnimatedDivider(
    modifier: Modifier = Modifier,
    state: LazyListState,
) {
    val isVisible = state.canScrollBackward

    AnimatedVisibility(
        visible = isVisible,
    ) {
        Divider(
            modifier = Modifier,
            thickness = YacsaTheme.spacing.small,
            color = YacsaTheme.colors.secondary
        )
    }
}

@Preview
@Composable
fun Preview_AnimatedDivider() {
    YacsaTheme {
        AnimatedDivider(
            state = rememberLazyListState(),
        )
    }
}

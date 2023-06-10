package dev.yacsa.ui.composable.content

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ContentError(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(YacsaTheme.colors.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(1f)

        ) {
            Box(
                modifier = Modifier
                    .padding(YacsaTheme.spacing.medium)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(size =YacsaTheme.corners.medium))
                    .align(Alignment.Center)
                    .background(YacsaTheme.colors.surface),
                contentAlignment = Alignment.Center

            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .padding(YacsaTheme.spacing.medium),
                        painter = painterResource(id = R.drawable.illustration_error),
                        contentDescription = null,
                    )
                    Text(
                        text = "SWW",
                        style = YacsaTheme.typography.header,
                        color = YacsaTheme.colors.primary,
                    )
                    Spacer(
                        modifier = Modifier.height(YacsaTheme.spacing.small),
                    )
                    Text(
                        text = errorMessage,
                        style = YacsaTheme.typography.title,
                        color = YacsaTheme.colors.secondary,
                    )
                    OutlinedButton(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(horizontal = YacsaTheme.spacing.medium),
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = YacsaTheme.colors.surface,
                            contentColor = YacsaTheme.colors.accent
                        ),
                        border = BorderStroke(YacsaTheme.dividers.small, YacsaTheme.colors.accent),
                        onClick = {
                            onBackClick()
                        }) {
                        androidx.compose.material.Text(
                            text = "Okay",
                            style = YacsaTheme.typography.title,
                            color = YacsaTheme.colors.accent
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentError() {
    YacsaTheme() {
        ContentError(
            errorMessage = "Foobar",
            onBackClick = {}
        )
    }
}

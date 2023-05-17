package dev.yacsa.ui.composable.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ContentError(
    modifier: Modifier = Modifier,
    errorMessage:String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(size = 16.dp))
                .align(Alignment.Center)
                .background(Color(0xFFE0DFFD)),
            contentAlignment = Alignment.Center

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .padding(16.dp),
                    painter = painterResource(id = R.drawable.illustration_error),
                    contentDescription = null,
                )
                Text(text = "SWW", style = YacsaTheme.typography.title)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = errorMessage, style = YacsaTheme.typography.caption)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentError() {
    YacsaTheme() {
        ContentError(
            errorMessage="Foobar"
        )
    }
}

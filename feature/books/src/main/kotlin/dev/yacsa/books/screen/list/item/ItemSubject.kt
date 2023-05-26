package dev.yacsa.books.screen.list.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemSubject(
    subject: String,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(16.dp)
    Card(
        shape = shape,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
            ) {
                onClick()
            },
        border = BorderStroke(1.dp, Color(0xFF7766C6)),
        elevation = 10.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SmallFloatingActionButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_mask_happy_regular_24),
                    contentDescription = null,
                )
            }
            Spacer(
                modifier = Modifier
                    .width(16.dp),
            )

            Text(
                modifier = Modifier.basicMarquee(),
                text = subject,
                style = YacsaTheme.typography.title
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ItemSubject() {
    YacsaTheme {
        ItemSubject(
            subject= "Abactus peritus homo est.",
            onClick = {},
        )
    }
}
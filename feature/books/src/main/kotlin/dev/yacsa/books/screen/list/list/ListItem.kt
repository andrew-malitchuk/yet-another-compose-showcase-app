package dev.yacsa.books.screen.list.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Row(
        modifier = modifier.fillMaxWidth().clickable {

        }
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
        ) {
            Text(
                text = title,
                style = YacsaTheme.typography.heading
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text=description,
                style = YacsaTheme.typography.caption
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun Preview_ListItem_Dark() {
    YacsaTheme {
        ListItem(
            title = "title",
            description = "description"
        )
    }
}

@Preview
@Composable
fun Preview_ListItem_Light() {
    YacsaTheme {

    }
}
package dev.yacsa.books.screen.detalization.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun SubjectBlock(
    modifier: Modifier = Modifier,
    subjects: List<String>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        subjects.forEach {
            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(imageVector = Icons.Outlined.PlayArrow, contentDescription = "")
                Spacer(modifier = Modifier.width(YacsaTheme.spacing.small))
                Text(text = it)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_SubjectBlock_Light() {
    YacsaTheme(false) {
        SubjectBlock(
            subjects = listOf("Lorem", "Ipsum", "Dolor", "Sit", "Amen"),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_SubjectBlock_Dark() {
    YacsaTheme(true) {
        SubjectBlock(
            subjects = listOf("Lorem", "Ipsum", "Dolor", "Sit", "Amen"),
        )
    }
}

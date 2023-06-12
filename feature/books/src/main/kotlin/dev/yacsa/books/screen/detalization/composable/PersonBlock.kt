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
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.model.model.PersonUiModel
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun PersonBlock(
    modifier: Modifier = Modifier,
    authors: List<PersonUiModel>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        authors.forEach {
            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(imageVector = Icons.Outlined.Person, contentDescription = "")
                Spacer(modifier = Modifier.width(YacsaTheme.spacing.small))
                Column() {
                    Text(text = "${it.birthYear}-${it.deathYear}")
                    Text(text = it.name ?: "SWW")
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_PersonBlock_Light() {
    YacsaTheme(false) {
        PersonBlock(
            authors = listOf(
                PersonUiModel(
                    0,
                    2000,
                    3000,
                    "Foo Bar",
                ),
            ),
        )
    }
}


@Composable
@Preview(showBackground = true)
fun Preview_PersonBlock_Dark() {
    YacsaTheme(true) {
        PersonBlock(
            authors = listOf(
                PersonUiModel(
                    0,
                    2000,
                    3000,
                    "Foo Bar",
                ),
            ),
        )
    }
}

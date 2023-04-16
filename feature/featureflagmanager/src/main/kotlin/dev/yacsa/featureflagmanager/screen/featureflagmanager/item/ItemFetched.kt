package dev.yacsa.featureflagmanager.screen.featureflagmanager.item

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ItemFetched(
    modifier: Modifier = Modifier,
    title: String,
    isEnabled: (Boolean) -> Unit,
    isActive: (Boolean) -> Unit
) {
    val checkboxState = remember { mutableStateOf(false) }
    val switchState = remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        8.dp
                    ),
                text = title
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = switchState.value,
                onCheckedChange = {
                    switchState.value = it
                    isActive(it)
                },
                enabled = checkboxState.value
            )
            Checkbox(
                checked = checkboxState.value,
                onCheckedChange = {
                    checkboxState.value = it
                    isEnabled(it)
                },
                modifier = Modifier
                    .padding(
                        16.dp
                    )
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_ItemFetched() {
    YacsaTheme {
        ItemFetched(
            title = "foo",
            isActive = {},
            isEnabled = {}
        )
    }
}
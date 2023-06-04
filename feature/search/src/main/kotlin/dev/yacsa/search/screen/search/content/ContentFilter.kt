package dev.yacsa.search.screen.search.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.search.screen.composable.ChipGroup
import dev.yacsa.ui.composable.button.RowToggleButtonGroup
import dev.yacsa.ui.composable.keyboard.clearFocusOnKeyboardDismiss
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ContentFilter() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Box(modifier = Modifier.fillMaxWidth()) {
            RowToggleButtonGroup(
                modifier = Modifier,
                buttonCount = 3,
                selectedColor = Color.Gray,
                unselectedColor = Color.LightGray,
                selectedContentColor = Color.White,
                unselectedContentColor = DarkGray,
                elevation = ButtonDefaults.elevation(0.dp), // elevation of toggle group buttons
                buttonIcons = arrayOf(
                    painterResource(id = dev.yacsa.ui.R.drawable.icon_sort_ascending_regular_24),
                    painterResource(id = dev.yacsa.ui.R.drawable.icon_sort_descending_regular_24),
                    painterResource(id = dev.yacsa.ui.R.drawable.icon_heart_regulat_24),
                ),
                buttonHeight=36.dp
            ) { index ->
                // check index and handle click
            }
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clearFocusOnKeyboardDismiss(),
            value = "",
            onValueChange ={},
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            },
            trailingIcon = {
                IconButton(onClick = {
//                    onValueChange("")
                }) {
                    Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
                }
            },
            singleLine = true,
            placeholder = { Text("Topic") },
        )

        ChipGroup(
            values = listOf("foo","bar"),
            defaultColor = YacsaTheme.colors.primaryText,
            selectedColor = YacsaTheme.colors.statusBarColor,
            onSelectedChanged = {
//                onValueChange(it)
            },
        )

    }
}

@Preview(showBackground=true)
@Composable
fun Preview_ContentFilter() {
    YacsaTheme(){
        ContentFilter()
    }
}
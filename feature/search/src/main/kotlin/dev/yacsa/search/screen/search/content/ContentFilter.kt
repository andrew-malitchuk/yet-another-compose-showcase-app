package dev.yacsa.search.screen.search.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.ui.composable.button.RowToggleButtonGroup
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
            ) { index ->
                // check index and handle click
            }
        }
    }
}

@Preview(showBackground=true)
@Composable
fun Preview_ContentFilter() {
    YacsaTheme(){
        ContentFilter()
    }
}
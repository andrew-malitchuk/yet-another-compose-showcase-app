package dev.yacsa.search.screen.search.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.button.RowToggleButtonGroup
import dev.yacsa.ui.theme.YacsaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterDialog(
    onDismiss: () -> Unit,
    onSort: (FilterDialogResult) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        FilterDialogContent(
            onSort
        )
    }

}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
fun FilterDialogContent(
    onSort: (FilterDialogResult) -> Unit
) {

    val languages = listOf<String>("en", "fr", "ge")
    var chipState by remember { mutableStateOf("") }

    val filterResult = remember {
        FilterDialogResult()
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(vertical = 16.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            androidx.compose.material.Text(
                text = "Filter stuff",
                style = YacsaTheme.typography.heading,
            )
        }
        Divider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            androidx.compose.material.Text(
                text = "Language",
                style = YacsaTheme.typography.caption,
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                languages.forEach {
                    FilterChip(
                        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                        // TODO: fix
                        border = BorderStroke(1.dp,Color.Gray),
                        onClick = {
                            filterResult.lang = it
                            chipState = it
                        },
                        selected = (chipState == it),
                        content = {
                            Text(
                                text = it,
                                color = YacsaTheme.colors.primaryText,
                            )
                        },
                    )
//                    AssistChip(
//                        onClick = {
//                            filterResult.lang = it
//                        },
//
//                        label = {
//                            Text(
//                                text = it,
//                                color = YacsaTheme.colors.primaryText,
//                            )
//                        }
//                    )
                }

            }
            Spacer(modifier = Modifier.height(6.dp))
            androidx.compose.material.Text(
                text = "Sort",
                style = YacsaTheme.typography.caption,
            )
            RowToggleButtonGroup(
                modifier = Modifier,
                buttonCount = 3,
                selectedColor = Color.Gray,
                unselectedColor = Color.LightGray,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.DarkGray,
                elevation = ButtonDefaults.elevation(0.dp), // elevation of toggle group buttons
                buttonIcons = arrayOf(
                    painterResource(id = R.drawable.icon_sort_ascending_regular_24),
                    painterResource(id = R.drawable.icon_sort_descending_regular_24),
                    painterResource(id = R.drawable.icon_heart_regulat_24),
                ),
                buttonHeight = 36.dp
            ) { index ->
                // check index and handle click
                when (index) {
                    0 -> filterResult.sort = "ascending"
                    1 -> filterResult.sort = "descending"
                    2 -> filterResult.sort = "popular"
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(modifier = Modifier.fillMaxWidth())
        Spacer(
            modifier = Modifier.height(6.dp)
        )
        OutlinedButton(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            onClick = {
                onSort(filterResult)
            }) {
            Text("Filter it!")
        }
    }
}

data class FilterDialogResult(
    var lang: String? = null,
    var sort: String? = null
)

@Preview(showBackground = true)
@Composable
fun Preview_FilterDialogContent() {
    YacsaTheme {
        FilterDialogContent(
            onSort = {

            }
        )
    }
}
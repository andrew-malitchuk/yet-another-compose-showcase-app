package dev.yacsa.search.screen.search.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    onSort: (FilterDialogResult) -> Unit,
    previousContent: FilterDialogResult?,
    onClear: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        containerColor = YacsaTheme.colors.surface,
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        FilterDialogContent(
            onSort,
            previousContent,
            onClear
        )
    }

}

@OptIn(
    ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
fun FilterDialogContent(
    onSort: (FilterDialogResult) -> Unit,
    previousContent: FilterDialogResult?,
    onClear: () -> Unit
) {

    val prevLang = previousContent?.lang
    val prevSort = previousContent?.sort

    val languages = listOf("en", "fr", "ge")
    var chipState by remember { mutableStateOf("") }

    val filterResult = remember {
        FilterDialogResult(
            prevLang,
            prevSort
        )
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(bottom = 16.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            androidx.compose.material.Text(
                text = "Filter stuff",
                style = YacsaTheme.typography.header,
                color = YacsaTheme.colors.primary
            )
            Spacer(modifier = Modifier.weight(1f))
            SmallFloatingActionButton(
                onClick = { onClear() },
                containerColor = YacsaTheme.colors.accent
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_trash_regular_24),
                    contentDescription = null,
                    tint = YacsaTheme.colors.primary
                )
            }
        }
        Divider(modifier = Modifier.fillMaxWidth(), color = YacsaTheme.colors.background)
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            androidx.compose.material.Text(
                text = "Language",
                style = YacsaTheme.typography.caption,
                color = YacsaTheme.colors.secondary
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                languages.forEach {
                    FilterChip(
                        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                        colors = androidx.compose.material3.FilterChipDefaults.filterChipColors(
                            containerColor = YacsaTheme.colors.surface,
                            selectedContainerColor = YacsaTheme.colors.background,
                            labelColor = YacsaTheme.colors.secondary,
                            selectedLabelColor = YacsaTheme.colors.primary,
                        ),
                        // TODO: fix
                        border = FilterChipDefaults.filterChipBorder(
                            borderColor = YacsaTheme.colors.secondary,
                            selectedBorderColor = YacsaTheme.colors.primary,
                            borderWidth = 1.dp,
                            selectedBorderWidth = 1.dp,
                        ),
                        onClick = {
                            filterResult.lang = it
                            chipState = it
                        },
                        selected = (chipState == it) || (prevLang == it),
                        label = {
                            Text(
                                text = it,
                                color = YacsaTheme.colors.primary,
                            )
                        },
                    )
                }

            }
            Spacer(modifier = Modifier.height(6.dp))
            androidx.compose.material.Text(
                text = "Sort",
                style = YacsaTheme.typography.caption,
                color = YacsaTheme.colors.secondary
            )
            RowToggleButtonGroup(
                primarySelection = filterResult.getSortIndex(prevSort) ?: -1,
                modifier = Modifier,
                buttonCount = 3,
                selectedColor = YacsaTheme.colors.background,
                unselectedColor = YacsaTheme.colors.surface,
                selectedContentColor = YacsaTheme.colors.primary,
                unselectedContentColor = YacsaTheme.colors.secondary,
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
        Divider(modifier = Modifier.fillMaxWidth(), color = YacsaTheme.colors.background)
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedButton(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = YacsaTheme.colors.surface,
                contentColor = YacsaTheme.colors.accent
            ),
            border = BorderStroke(1.dp, YacsaTheme.colors.accent ),
            onClick = {
                onSort(filterResult)
            }) {
            androidx.compose.material.Text(
                text = "Sort",
                style = YacsaTheme.typography.caption,
                color = YacsaTheme.colors.accent
            )
        }
    }
}

data class FilterDialogResult(
    var lang: String? = null,
    var sort: String? = null
) {

    private val sortTypes = arrayListOf<String>("ascending", "descending", "popular")

    fun getSortIndex(value: String?): Int? {
        return if (sortTypes.contains(value)) {
            sortTypes.indexOf(value)
        } else {
            null
        }
    }

    fun isFulfilled(): Boolean {
        return lang != null || sort != null
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_FilterDialogContent() {
    YacsaTheme {
        FilterDialogContent(
            onSort = {

            },
            previousContent = null,
            {}
        )
    }
}
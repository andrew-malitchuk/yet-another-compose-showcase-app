package dev.yacsa.ui.composable.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.yacsa.ui.theme.YacsaTheme
import dev.yacsa.update.model.UpdateModel

@Composable
fun UpdateDialog(
    modifier: Modifier,
    updateModel: UpdateModel,
//    showDialog: MutableStateFlow<Boolean>,
    showDialog: (Boolean) -> Unit,
    confirmClick: () -> Unit,
    dismissClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
//            showDialog.value = false
            showDialog(false)
        },

        title = {
            Text(
                text = updateModel.title ?: "SWW",
                style = YacsaTheme.typography.caption,
                color = YacsaTheme.colors.primary
            )
        },
        text = {
            Text(
                text = updateModel.content ?: "SWW",
                style = YacsaTheme.typography.title,
                color = YacsaTheme.colors.secondary
            )
        },
        confirmButton = {
            TextButton(onClick = { confirmClick() }) {
                Text("ok")
            }
        },
        dismissButton = {
            TextButton(onClick = {
//                showDialog.value = false
                showDialog(false)
                dismissClick()
            }) {
                Text("Cancel")
            }
        },
    )
}
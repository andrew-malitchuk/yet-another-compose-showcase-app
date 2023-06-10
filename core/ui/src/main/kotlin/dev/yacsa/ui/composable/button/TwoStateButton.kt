package dev.yacsa.ui.composable.button

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun TwoStateButton(
    modifier:Modifier=Modifier,
    checkedState: MutableState<Boolean?>,
    @DrawableRes
    defaultIcon: Int,
    @DrawableRes
    selectedIcon: Int,
    onButtonClick: ((Boolean) -> Unit)?=null,
    containerColor: Color =YacsaTheme.colors.primary

) {
//    val checked = remember { mutableStateOf(false) }
    SmallFloatingActionButton(
        elevation = FloatingActionButtonDefaults.elevation(0.dp),
        onClick = {
            onButtonClick?.invoke(checkedState.value?:false)
        },
        containerColor = containerColor
    ) {
        IconToggleButton(checked = checkedState.value?:false, onCheckedChange = { checkedState.value = it }) {

            Crossfade(
                targetState = checkedState.value
            ) { isChecked ->
                isChecked?.let { onButtonClick?.invoke(it) }
                if (isChecked==true) {
                    Icon(
                        painter = painterResource(id = selectedIcon), contentDescription = null, tint = YacsaTheme.colors.accent)
                } else {
                    Icon(
                        painter = painterResource(id = defaultIcon), contentDescription = null,tint = YacsaTheme.colors.accent)
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun Preview_TwoStateButton() {
    YacsaTheme {
        TwoStateButton(
            Modifier,
            remember { mutableStateOf(false) },
            R.drawable.icon_search_regular_24,
            R.drawable.icon_heart_regulat_24,
            {},
            YacsaTheme.colors.primary
        )
    }
}
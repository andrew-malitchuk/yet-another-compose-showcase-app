package dev.yacsa.books.screen.detalization.composable.toolbar

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import dev.yacsa.books.R
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun Bar() {
    val lazyScrollState = rememberLazyListState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CollapsingToolbar(lazyScrollState)
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .animateContentSize(),
                state = lazyScrollState
            ) {
                items(100) { index ->
                    Text(modifier = Modifier.padding(36.dp), text = "Item: $index")
                    Divider(color = Color.Black, thickness = 1.dp)
                }

            }
        }
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun CollapsingToolbar(lazyScrollState: LazyListState) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.collapse_toolbar).readBytes().decodeToString()
    }

    val progress by animateFloatAsState(
        targetValue = if (lazyScrollState.firstVisibleItemIndex in 0..1) 0f else 1f,
        tween(500)
    )
    val motionHeight by animateDpAsState(
        targetValue = if (lazyScrollState.firstVisibleItemIndex in 0..1) 230.dp else 56.dp,
        tween(500)
    )

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Cyan)
            .height(motionHeight)
    ) {

        val boxProperties = motionProperties(id = "box")
//        val startColor = Color(boxProperties.value.color("custome"))
        Box(
            modifier = Modifier
                .layoutId("box")
                .background(boxProperties.value.color("background"))
        )

        Image(
            modifier = Modifier
                .layoutId("help_image"),
            painter = painterResource(id = dev.yacsa.ui.R.drawable.illustration_error),
            contentDescription = ""
        )

        Icon(
            modifier = Modifier.layoutId("close_button"),
            imageVector = Icons.Filled.Close,
            contentDescription = "",
            tint = Color.White
        )

        Text(
            modifier = Modifier.layoutId("title"),
            text = "Help",
            color = Color.White,
            fontSize = 18.sp
        )

    }
}

@Preview(showBackground = true)
@Composable
fun  Bar_Bar(){
    YacsaTheme {
        Bar()
    }
}
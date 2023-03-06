package dev.yacsa.app

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.app.ui.theme.YACSATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YACSATheme {
                ScrollableScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YACSATheme {
//        TextScreen()
        ScrollableScreen()
    }
}

@Composable
fun TextScreen() {
    Column(
//        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MyText()
        MyTextField()
        FooRow()
        MyRdBtn()
        MyFAB()
        Progress()
        Dialog()
        FooBox()
        FooSurface()
    }
}

@Composable
fun MyText() {
    Text(
        text = stringResource(id = R.string.app_name),
        fontFamily = FontFamily.Monospace,
        color = Color.Cyan,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MyTextField() {
    val textValue = remember { mutableStateOf("") }
    OutlinedTextField(
        label = {
            Text("foobar")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType
            = KeyboardType.Email
        ),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
    )
}

@Composable
fun MyFAB() {
    FloatingActionButton(
        onClick = {

        },
        backgroundColor = Color.Cyan,
    ) {

    }
}

@Composable
fun MyBtn() {
    Button(
        onClick = {
            Log.d("foo", "click")
        }
    ) {
        Text(text = "foo")
    }
}

@Composable
fun MyBtnOutline() {
    OutlinedButton(
        onClick = {

        }
    ) {
        Text(text = "Show dialog")
    }
}

@Composable
fun MyRdBtn() {
    val foo = listOf(0, 1, 2)
    val selected = remember {
        mutableStateOf(foo.first())
    }
    Column {
        foo.forEach {
            val isSelected = it == selected.value
            RadioButton(
                selected = isSelected,
                onClick = {
                    selected.value = it
                }
            )
        }
    }
}

@Composable
fun Progress() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        LinearProgressIndicator(progress = 0.5f)
    }
}

@Composable
fun Dialog() {
    val shouldShowDialog = remember {
        mutableStateOf(true)
    }
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
            },
            title = { Text(text = "title") },
            text = { Text(text = "text") },
            confirmButton = {
                Button(onClick = {
                    shouldShowDialog.value = false
                }) {
                    Text(text = "confirm")
                }
            }
        )
    }
}

@Composable
fun FooRow() {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        MyBtn()
        MyBtnOutline()
        MyBtn()
    }
}

@Composable
fun FooBox() {
    Box() {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
        LinearProgressIndicator(
            progress = 0.5f,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun FooSurface() {
    Surface(
        modifier = Modifier.size(100.dp),
        color = Color.Cyan,
        contentColor = Color.Black,
        elevation = 10.dp,
        border = BorderStroke(1.dp, Color.Yellow)
    ) {
        Text(text = "FooSurface")

    }
}

@Composable
fun ListItem(
    value: Int, modifier: Modifier =
        Modifier
) {
    Text(text = "$value")
}

@Composable
fun ScrollableScreen() {
    
    val list = listOf(1,2,3,4,5,6,7,8,9,10)
    LazyColumn {
        items(list){
            ListItem(value = it)
        }
    }

}
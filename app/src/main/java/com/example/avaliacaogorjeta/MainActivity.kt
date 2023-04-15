package com.example.avaliacaogorjeta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.avaliacaogorjeta.ui.theme.AvaliacaoGorjetaTheme
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AvaliacaoGorjetaTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    apresentacao()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AvaliacaoGorjetaTheme {
        Greeting("Android")
    }
}

@Composable
fun apresentacao(){
    val amount = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Amount",
            style = MaterialTheme.typography.subtitle1
        )

        OutlinedTextField(
            value = amount.value,
            onValueChange = {
                amount.value = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        Text(
            text = "Custom (%):",
            style = MaterialTheme.typography.subtitle1
        )
        var sliderPosition by remember { mutableStateOf(0f) }
        Column {
            Text(text = sliderPosition.toString())
            Slider(
                modifier = Modifier.semantics { contentDescription = "Localized Description" },
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it.roundToInt().toFloat()
                    //calcular gorjeta
                                },
                valueRange = 0f..30f,
                steps = 1,
                )
        }

        Text(
            text = "15%",
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = "Tip",
            style = MaterialTheme.typography.subtitle1
        )
        OutlinedTextField(
            value = amount.value,
            onValueChange = {
                amount.value = it //15tip
            },
        )
        Text(
            text = "Total",
            style = MaterialTheme.typography.subtitle1
        )
        OutlinedTextField(
            value = amount.value,
            onValueChange = {
                amount.value = it //total+15tip
            },
        )
        Text(
            text = "value" + "%",
            style = MaterialTheme.typography.subtitle1
        )
        OutlinedTextField(
            value = amount.value,
            onValueChange = {
                amount.value = it //total+%tip
            },
        )

    }

}

@Composable
fun Thumb(interactionSource: Any, thumbSize: Any) {

}




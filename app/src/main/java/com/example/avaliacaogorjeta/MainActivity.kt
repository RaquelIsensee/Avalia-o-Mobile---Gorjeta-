package com.example.avaliacaogorjeta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
fun apresentacao(){
    val amount = remember {
        mutableStateOf("")
    }
    val total15 = remember {
        mutableStateOf("")
    }
    val total = remember {
        mutableStateOf("")
    }
    val tip15 = remember {
        mutableStateOf("")
    }
    val customTip = remember {
        mutableStateOf("")
    }
    var sliderPosition by remember { mutableStateOf(18.toFloat()) }

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(text = "Tip Calculator")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Amount") },
            value = amount.value,
            onValueChange = {
                amount.value = it
                total15.value = (it.toFloat() * 0.15 + it.toFloat()).toString()
                tip15.value = (it.toFloat() * 0.15).toString()
                customTip.value = calcCustomTip(amount.value.toFloat(), sliderPosition).toString()
                total.value = calcTotalCustomTip(amount.value.toFloat(), sliderPosition).toString()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        Text(
            text = "Custom (%): " + sliderPosition.toString()
        )
        Slider(
            modifier = Modifier.semantics { contentDescription = "Localized Description" },
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it.roundToInt().toFloat()
                customTip.value =
                    calcCustomTip(amount.value.toFloat(), sliderPosition).toString()
                total.value =
                    calcTotalCustomTip(amount.value.toFloat(), sliderPosition).toString()
            },
            valueRange = 0f..30f,
            steps = 30,
        )

        Row() {
            Column(modifier = Modifier
                .width(155.dp)
                .padding(10.dp)) {
                OutlinedTextField(

                    value = tip15.value,
                    onValueChange = {
                        tip15.value = it //total+15tip
                    },
                    readOnly = true,
                    label = { Text(text = "15% Tip") }
                )
                OutlinedTextField(
                    value = total15.value,
                    onValueChange = {
                        total15.value = it //total+15tip
                    },
                    readOnly = true,
                    label = { Text(text = "Total 15% Tip") }
                )
            }
            Column(modifier = Modifier
                .width(155.dp)
                .padding(10.dp)) {
                OutlinedTextField(
                    label = { Text(text = sliderPosition.roundToInt().toString() + "% Tip") },
                    value = customTip.value,
                    onValueChange = {
                        customTip.value = it //total+%tip
                    },
                    readOnly = true,
                )
                OutlinedTextField(
                    label = { Text(text = "Total " + sliderPosition.roundToInt().toString() + "% Tip") },
                    value = total.value,
                    onValueChange = {
                        total.value = it //total+%tip
                    },
                    readOnly = true,
                )
            }

        }

    }

}

fun calcCustomTip(amount: Float, customTip: Float): Float {
    return (amount * customTip /100)
}
fun calcTotalCustomTip(amount: Float, customTip: Float): Float{
    return ((amount * customTip /100) + amount)
}




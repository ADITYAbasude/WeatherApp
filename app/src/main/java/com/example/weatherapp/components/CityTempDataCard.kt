package com.example.weatherapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CityTempDataCard(
    locationName: MutableState<String>,
    temperature: MutableState<String>,
    description: MutableState<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(all = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = locationName.value,
            fontSize = 35.sp,
        )

        Spacer(modifier = Modifier.height(50.dp))

        Row() {
            Text(
                text = temperature.value,
                fontSize = 60.sp,
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.height(60.dp)
            ) {
                Text(
                    text = "℃",
//                    textAlign = TextAlign.Start
                )
            }
        }
        Text(
            text = description.value,
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
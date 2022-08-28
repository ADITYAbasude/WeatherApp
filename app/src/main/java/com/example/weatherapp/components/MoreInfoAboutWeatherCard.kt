package com.example.weatherapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.gray


@Composable
fun MoreInfoAboutWeatherCard(type: String, data: MutableState<String>) {
    Surface(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .width(150.dp),
        color = gray
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(15.dp)
        ) {
            Text(text = type, fontSize = 22.sp, modifier = Modifier.padding(bottom = 15.dp))
            Text(text = data.value , fontSize = 18.sp)
        }
    }
}

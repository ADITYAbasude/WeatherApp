package com.example.weatherapp.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.constants.ApiCalls.LocationData
import com.example.weatherapp.ui.theme.gray

@Composable
fun SearchTextField(
    searchValue: MutableState<String>,
    locationName: MutableState<String>,
    lat: MutableState<String>,
    lon: MutableState<String>,
    context: Context,
    temperature: MutableState<String>,
    humidity: MutableState<String>,
    feelsLike: MutableState<String>,
    visibility: MutableState<String>,
    airPressure: MutableState<String>,
    windsData: MutableState<String>,
    description: MutableState<String>
) {
    SmallTopAppBar(
        title = {
            TextField(
                value = searchValue.value,
                onValueChange = { searchValue.value = it },
                placeholder = {
                    Text(
                        text = "Search...",
                        fontSize = 15.sp,
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(end = 15.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = gray,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                singleLine = true,
                trailingIcon = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        IconButton(onClick = { searchValue.value = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                        IconButton(onClick = {
                            if (searchValue.value.trim() != "") {
                                LocationData().locationApi(
                                    context = context,
                                    lat = lat,
                                    lon = lon,
                                    locationName = locationName,
                                    search = searchValue,
                                    temperature = temperature,
                                    windsData = windsData,
                                    humidity = humidity,
                                    feelsLike = feelsLike,
                                    visibility = visibility,
                                    airPressure = airPressure,
                                    description = description
                                )
                            } else {
                                Toast.makeText(
                                    context,
                                    "Enter the location name",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = null,
                                tint = Color.Black

                            )
                        }
                    }
                }


            )

        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.White,
        ),
    )
}
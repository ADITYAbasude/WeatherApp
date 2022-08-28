@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.weatherapp.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.weatherapp.components.MoreInfoAboutWeatherCard
import com.example.weatherapp.constants.ApiCalls.LocationData
import com.example.weatherapp.ui.theme.gray

class MainActivity : ComponentActivity() {
    lateinit var locationName: MutableState<String>
    lateinit var lat: MutableState<String>
    lateinit var lon: MutableState<String>
    lateinit var searchValue: MutableState<String>
    lateinit var temperature: MutableState<String>
    lateinit var windsData: MutableState<String>
    lateinit var feelsLike: MutableState<String>
    lateinit var humidity: MutableState<String>
    lateinit var airPressure: MutableState<String>
    lateinit var visibility: MutableState<String>
    lateinit var description: MutableState<String>
    var start: Boolean = true
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme() {
                val scaffoldState = rememberScaffoldState()
                locationName = remember {
                    mutableStateOf("")
                }
                lat = remember {
                    mutableStateOf("")
                }
                lon = remember {
                    mutableStateOf("")
                }
                searchValue = remember {
                    mutableStateOf("Bhiwandi")
                }
                temperature = remember {
                    mutableStateOf("")
                }
                windsData = remember {
                    mutableStateOf("")
                }
                feelsLike = remember {
                    mutableStateOf("")
                }
                visibility = remember {
                    mutableStateOf("")
                }
                airPressure = remember {
                    mutableStateOf("")
                }
                humidity = remember {
                    mutableStateOf("")
                }
                description = remember {
                    mutableStateOf("")
                }
                if (searchValue.value.isNotEmpty() && start ) {
                    LocationData().locationApi(
                        context = this@MainActivity,
                        locationName = locationName,
                        lat = lat,
                        lon = lon,
                        search = searchValue,
                        temperature = temperature,
                        windsData = windsData,
                        humidity = humidity,
                        feelsLike = feelsLike,
                        visibility = visibility,
                        airPressure = airPressure,
                        description = description
                    )
                    start = !start
                }

                Scaffold(
                    scaffoldState = scaffoldState,
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
                    topBar = {
                        SearchTextField(
                            searchValue = searchValue,
                            lat = lat,
                            lon = lon,
                            locationName = locationName,
                            context = this@MainActivity,
                            temperature = temperature,
                            windsData = windsData,
                            humidity = humidity,
                            feelsLike = feelsLike,
                            visibility = visibility,
                            airPressure = airPressure,
                            description = description
                        )
                    },
                    content = {
                        Column(
                            Modifier
                                .background(MaterialTheme.colorScheme.background)
                                .fillMaxSize()
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 30.dp)
                                    .clip(shape = RoundedCornerShape(10.dp)),

                                color = gray
                            ) {
                                CityTempDataCard(
                                    locationName = locationName,
                                    temperature = temperature,
                                    description = description,
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .padding(top = 20.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                MoreInfoAboutWeatherCard(type = "Wind", data = windsData)
                                MoreInfoAboutWeatherCard(type = "Humidity", data = humidity)
                            }
                            Row(
                                modifier = Modifier
                                    .padding(top = 20.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                MoreInfoAboutWeatherCard(type = "Feels Like", data = feelsLike)
                                MoreInfoAboutWeatherCard(type = "Visibility", data = visibility)
                            }

                            Row(
                                modifier = Modifier
                                    .padding(top = 20.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                MoreInfoAboutWeatherCard(type = "Air Pressure", data = airPressure)
                            }
                        }
                    }
                )
            }
        }

    }

}


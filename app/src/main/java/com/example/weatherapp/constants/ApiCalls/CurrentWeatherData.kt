package com.example.weatherapp.constants.ApiCalls

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.example.weatherapp.constants.ApiKeys
import com.example.weatherapp.constants.RetrofitModule
import com.example.weatherapp.model.GeoCoordinate.GeoCoordinate
import com.example.weatherapp.model.currentWeather.CurrentWeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt

class CurrentWeatherData {

    fun currentWeatherApi(
        context: Context,
        lat: Double,
        lon: Double,
        temperature: MutableState<String>,
        windsData: MutableState<String>,
        humidity: MutableState<String>,
        feelsLike: MutableState<String>,
        visibility: MutableState<String>,
        airPressure: MutableState<String>,
        description: MutableState<String>,
    ) {
        RetrofitModule.retrofitApiProvider().getWeatherData(
            apiKey = ApiKeys().apiKeys,
            lat = lat,
            lon = lon,
            units = "metric"
        ).enqueue(object : Callback<CurrentWeatherData> {
            override fun onResponse(
                call: Call<CurrentWeatherData>,
                response: Response<CurrentWeatherData>
            ) {
                val res: CurrentWeatherData = response.body()!!
                temperature.value = res.main.temp.roundToInt().toString()
                windsData.value = res.wind.speed.toString() + "Km/h"
                humidity.value = res.main.humidity.toString() + "%"
                feelsLike.value = res.main.feels_like.toString() + "℃"
                visibility.value = (res.visibility / 1000).toString() + " km"
                airPressure.value = res.main.pressure.toString() + " hPa"
                description.value = res.weather[0].description
            }

            override fun onFailure(call: Call<CurrentWeatherData>, t: Throwable) {
                Log.i("error", t.message.toString())
                Toast.makeText(context, t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }
}

package com.example.weatherapp.constants.ApiCalls

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.example.weatherapp.constants.ApiKeys
import com.example.weatherapp.constants.RetrofitModule
import com.example.weatherapp.model.GeoCoordinate.GeoCoordinate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationData {

    fun locationApi(
        context: Context,
        locationName: MutableState<String>,
        lat: MutableState<String>,
        lon: MutableState<String>,
        search: MutableState<String>,
        temperature: MutableState<String>,
        windsData: MutableState<String>,
        humidity: MutableState<String>,
        feelsLike: MutableState<String>,
        visibility: MutableState<String>,
        airPressure: MutableState<String>,
        description: MutableState<String>
    ) {
        RetrofitModule.retrofitApiProvider().getCoordinate(
            apiKey = ApiKeys().apiKeys,
            q = search.value,
            limit = "1"
        ).enqueue(object : Callback<List<GeoCoordinate>> {
            override fun onResponse(
                call: Call<List<GeoCoordinate>>,
                response: Response<List<GeoCoordinate>>
            ) {
                val res: List<GeoCoordinate>? = response.body()

                if (res!!.isNotEmpty()) {
                    locationName.value = res[0].name
                    lat.value = res[0].lat.toString()
                    lon.value = res[0].lon.toString()

                    CurrentWeatherData().currentWeatherApi(
                        lat = res[0].lat,
                        lon = res[0].lon,
                        context = context,
                        temperature = temperature,
                        windsData = windsData,
                        humidity = humidity,
                        feelsLike = feelsLike,
                        visibility = visibility,
                        airPressure = airPressure,
                        description = description
                    )

                } else {
                    Toast.makeText(context, "No such location found", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<GeoCoordinate>>, t: Throwable) {
                Toast.makeText(context, t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }

}
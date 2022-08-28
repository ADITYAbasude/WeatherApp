package com.example.weatherapp

import com.example.weatherapp.model.GeoCoordinate.GeoCoordinate
import com.example.weatherapp.model.currentWeather.CurrentWeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi {
    @GET("geo/1.0/direct")
    fun getCoordinate(
        @Query("appid") apiKey: String,
        @Query("limit") limit: String,
        @Query("q") q: String
    ): Call<List<GeoCoordinate>>

    @GET("data/2.5/weather")
    fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") apiKey: String,
    ): Call<CurrentWeatherData>
}
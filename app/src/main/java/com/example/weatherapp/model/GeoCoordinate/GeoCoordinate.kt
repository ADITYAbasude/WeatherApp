package com.example.weatherapp.model.GeoCoordinate

//class GeoCoordinate : GeoCoordinateItem()

data class GeoCoordinate(
    val country: String,
    val lat: Double,
    val local_names: LocalNames,
    val lon: Double,
    val name: String,
    val state: String
)


data class LocalNames(
    val en: String,
    val hi: String,
    val ja: String,
    val kn: String,
    val mr: String,
    val pa: String,
    val ru: String,
    val ta: String
)
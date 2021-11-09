package com.itstep.weatherapp.rest.services

import GetWeatherRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather") //https://api.openweathermap.org/data/2.5/weather
    fun getWeatherInfo(
        @Query("id") id : String,
        @Query("appid") appid: String
    ): Call<GetWeatherRes>
}
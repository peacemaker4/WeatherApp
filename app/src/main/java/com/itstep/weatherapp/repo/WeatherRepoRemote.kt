package com.itstep.weatherapp.repo

import GetWeatherRes
import com.itstep.weatherapp.rest.RestManager
import com.itstep.weatherapp.rest.services.WeatherService
import com.itstep.weatherapp.utils.WeatherUrls
import retrofit2.Call

class WeatherRepoRemote {
    private val restManager = RestManager.instance!!

    private val service = restManager.createService(WeatherUrls.MAIN_URL, WeatherService::class.java)

    fun getWeatherInfo(id: String, appid: String) : Call<GetWeatherRes>{
        return service.getWeatherInfo(id, appid)
    }
}
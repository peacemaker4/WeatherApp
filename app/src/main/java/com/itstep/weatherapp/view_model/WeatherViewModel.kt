package com.itstep.weatherapp.view_model

import GetWeatherRes
import Main
import Wind
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itstep.weatherapp.db.models.WeatherInfo
import com.itstep.weatherapp.repo.WeatherRepoRemote
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class WeatherViewModel : ViewModel() {

    sealed class Event{
        class OnError(val msg: String) : Event()
    }

    val weatherItem = MutableLiveData<GetWeatherRes>()
    val event = MutableLiveData<Event>()
    private val repo = WeatherRepoRemote()

    //val db = AppDatabase

    fun getWeatherInfo(id: String, appid: String){
        repo.getWeatherInfo(id, appid).enqueue(object : retrofit2.Callback<GetWeatherRes?> {
            override fun onResponse(call: Call<GetWeatherRes?>, response: Response<GetWeatherRes?>) {
                if(response.isSuccessful){
                    if(response.body() != null){
                        weatherItem.value = response.body()
//                        GlobalScope.launch {
//                            db.weatherDao().insertWeather(WeatherInfo(id.toLong(), id, response.body()!!.main!!.temp, response.body()!!.main!!.humidity, response.body()!!.main!!.pressure, response.body()!!.wind!!.speed ))
//                        }
                    }
                    else{
                        onFailure(call, Throwable("No data"))
                    }
                }
                else{
//                    GlobalScope.launch {
//                        var weather = db.weatherDao().getWeather(id)
//                        weatherItem.value = GetWeatherRes(null, null, null, Main(weather.temp, weather.humidity, weather.pressure), Wind(weather.speed))
//                    }
                    onFailure(call, Throwable("Error response: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<GetWeatherRes?>, t: Throwable) {
                event.value = Event.OnError(t.message ?: "")
            }


        })
    }

}
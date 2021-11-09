package com.itstep.weatherapp.db.daos

import GetWeatherRes
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itstep.weatherapp.db.models.WeatherInfo

//@Dao
//interface WeatherDao {
//    @Query("SELECT * FROM weather_db WHERE id=:id")
//    suspend fun getWeather(id: String) : WeatherInfo
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertWeather(item: GetWeatherRes?)
//}
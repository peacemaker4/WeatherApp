package com.itstep.weatherapp.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_db")
data class WeatherInfo (
    @PrimaryKey
    val id: Long,
    val city_id: String,
    val temp: Float,
    val humidity: Float,
    val pressure: Float,
    val speed: Float
)
package com.itstep.weatherapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itstep.weatherapp.db.models.WeatherInfo
import com.itstep.weatherapp.view_model.WeatherViewModel

//@Database(entities = [
//    WeatherInfo::class
//], version = 1, exportSchema = false)
//abstract class AppDatabase : RoomDatabase(
//
//){
//    companion object {
//        @Volatile private var instance: AppDatabase? = null
//        private val LOCK = Any()
//
//        fun buildDatabase(context: Context) = Room.databaseBuilder(context,
//            AppDatabase::class.java, "weather_db")
//            .build()
//    }
//
//
//
//    abstract fun weatherDao() : WeatherDao
//}
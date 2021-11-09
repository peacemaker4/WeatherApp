package com.itstep.weatherapp.rest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.itstep.weatherapp.BuildConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestManager {
    companion object{

        var instance: RestManager? = null
        get(){
            if(field == null)
                field = RestManager()
            return field
        }
    }

    private val okHttpClient = createHttpClient()

    private fun createHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder
            .addInterceptor(createLoggingInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        return clientBuilder.build()
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if(BuildConfig.DEBUG){
            HttpLoggingInterceptor.Level.BODY
        }
        else{
            HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }

    fun <T> createService(baseUrl: String, service: Class<T>) : T{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(service)
    }

    private fun getGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }
}
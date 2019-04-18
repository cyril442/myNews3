package com.example.mynews

import android.app.Application
import com.example.mynews.NewYorkTimesApi.NytService
import com.example.mynews.NewYorkTimesApi.NytServiceSearch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {
        lateinit var instance : App
        private set

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

        private val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val nytService : NytService = retrofit.create(NytService::class.java)
        val nytServiceSearch : NytServiceSearch = retrofit.create(NytServiceSearch::class.java)
    }



    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
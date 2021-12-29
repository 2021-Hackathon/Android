package com.example.a2021hackthon.model.remote

import com.example.a2021hackthon.model.remote.dao.EmotionService
import com.example.a2021hackthon.model.remote.dao.SurveyService
import com.example.a2021hackthon.model.remote.dao.TimeService
import com.google.gson.Gson
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val BASE_URL = "http://ec2-18-191-238-179.us-east-2.compute.amazonaws.com:3000/"

    private val gson = Gson().newBuilder().setLenient().create()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createAsync())
        .baseUrl(BASE_URL)
        .build()

    val emotionService = retrofit.create(EmotionService::class.java)
    val surveyService = retrofit.create(SurveyService::class.java)
    val timeService = retrofit.create(TimeService::class.java)
}

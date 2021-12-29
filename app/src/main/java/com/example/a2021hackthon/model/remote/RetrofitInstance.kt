package com.example.a2021hackthon.model.remote

import com.example.a2021hackthon.model.remote.dao.EmotionService
import com.example.a2021hackthon.model.remote.dao.SurveyService
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val BASE_URL = "http://172.16.1.238:3000/"

    private val gson = Gson().newBuilder().setLenient().create()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .build()

    val emotionService = retrofit.create(EmotionService::class.java)
    val surveyService = retrofit.create(SurveyService::class.java)
}

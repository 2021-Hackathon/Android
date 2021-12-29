package com.example.a2021hackthon.model.remote

import com.example.a2021hackthon.model.remote.dao.EmotionService
import com.example.a2021hackthon.model.remote.dao.KakaoMapService
import com.example.a2021hackthon.model.remote.dao.SurveyService
import com.example.a2021hackthon.model.remote.dao.TimeService
import com.google.gson.Gson
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val BASE_URL = "http://ec2-18-191-238-179.us-east-2.compute.amazonaws.com:3000/"
    const val KAKAO_BASE_URL = "https://dapi.kakao.com/"

    private val gson = Gson().newBuilder().setLenient().create()

    private val okHttpClient = OkHttpClient.Builder().addInterceptor(ApiKeyInterceptor()).build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createAsync())
        .baseUrl(BASE_URL)
        .build()

    private val kakaoRetrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createAsync())
        .client(okHttpClient)
        .baseUrl(KAKAO_BASE_URL)
        .build()

    val emotionService = retrofit.create(EmotionService::class.java)
    val surveyService = retrofit.create(SurveyService::class.java)
    val timeService = retrofit.create(TimeService::class.java)
    val kakaoMapService = kakaoRetrofit.create(KakaoMapService::class.java)
}

class ApiKeyInterceptor : Interceptor {
    private val KAKAO_API_KEY = "4ea256ed66b34ce8113d08ee89e4d061"

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request().newBuilder().header("Authorization", "KakaoAK " + KAKAO_API_KEY).build()
        return chain.proceed(req)
    }
}
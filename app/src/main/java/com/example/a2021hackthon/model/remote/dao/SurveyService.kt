package com.example.a2021hackthon.model.remote.dao

import com.example.a2021hackthon.model.remote.dto.Food
import com.example.a2021hackthon.model.remote.dto.Res
import com.example.a2021hackthon.model.remote.dto.Survey
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SurveyService {
    @POST("/servey")
    fun postAnalyzeSurvey(@Body survey: Survey): Single<Response<Res<Food>>>
}
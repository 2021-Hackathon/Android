package com.example.a2021hackthon.model.remote.dao

import com.example.a2021hackthon.model.remote.dto.Food
import com.example.a2021hackthon.model.remote.dto.Res
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface TimeService {
    @GET("/")
    fun getAnalyzeTime(): Single<Response<Res<Food>>>
}
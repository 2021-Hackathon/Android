package com.example.a2021hackthon.model.remote.dao

import com.example.a2021hackthon.model.remote.dto.Documents
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoMapService {
    @GET("/v2/local/search/keyword.json")
    fun getSearchPlace(
        @Query("query") keyword: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): Single<Response<Documents>>
}
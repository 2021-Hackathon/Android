package com.example.a2021hackthon.model.remote.dao

import com.example.a2021hackthon.model.remote.dto.Food
import com.example.a2021hackthon.model.remote.dto.Res
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface EmotionService {

    @Multipart
    @POST("")
    fun postAnalyzeEmotion(
        @Part attachment: MultipartBody.Part
    ): Single<Response<Res<Food>>>
}

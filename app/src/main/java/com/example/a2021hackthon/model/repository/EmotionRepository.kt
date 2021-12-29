package com.example.a2021hackthon.model.repository

import com.example.a2021hackthon.model.remote.BaseRemote
import com.example.a2021hackthon.model.remote.dao.EmotionService
import com.example.a2021hackthon.model.remote.dto.Food
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody

class EmotionRepository(override val service: EmotionService) : BaseRemote<EmotionService>() {

    fun postAnalyzeEmotion(attachment: MultipartBody.Part): Single<Food> =
        service.postAnalyzeEmotion(attachment).map(getResponse())
}
package com.example.a2021hackthon.model.repository

import com.example.a2021hackthon.model.remote.BaseRemote
import com.example.a2021hackthon.model.remote.dao.EmotionService
import com.example.a2021hackthon.model.remote.dao.TimeService
import com.example.a2021hackthon.model.remote.dto.Food
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody

class TimeRepository (override val service: TimeService) : BaseRemote<TimeService>() {
    fun getAnalyzeTime(): Single<Food> =
        service.getAnalyzeTime().map(getResponse())
}
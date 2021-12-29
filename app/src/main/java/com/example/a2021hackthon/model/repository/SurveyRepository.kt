package com.example.a2021hackthon.model.repository

import com.example.a2021hackthon.model.remote.BaseRemote
import com.example.a2021hackthon.model.remote.dao.SurveyService
import com.example.a2021hackthon.model.remote.dto.Food
import com.example.a2021hackthon.model.remote.dto.Survey
import io.reactivex.rxjava3.core.Single

class SurveyRepository(override val service: SurveyService) : BaseRemote<SurveyService>() {
    fun postAnalyzeSurvey(survey: Survey): Single<Food> =
        service.postAnalyzeSurvey(survey).map(getResponse())
}
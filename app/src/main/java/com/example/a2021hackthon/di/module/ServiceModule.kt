package com.example.a2021hackthon.di.module

import com.example.a2021hackthon.model.remote.RetrofitInstance
import com.example.a2021hackthon.model.remote.dao.EmotionService
import com.example.a2021hackthon.model.remote.dao.KakaoMapService
import com.example.a2021hackthon.model.remote.dao.SurveyService
import com.example.a2021hackthon.model.remote.dao.TimeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideEmotionService(): EmotionService =
        RetrofitInstance.emotionService

    @Singleton
    @Provides
    fun provideSurveyService(): SurveyService =
        RetrofitInstance.surveyService

    @Singleton
    @Provides
    fun provideTimeService(): TimeService =
        RetrofitInstance.timeService

    @Singleton
    @Provides
    fun provideKakaoMapService(): KakaoMapService =
        RetrofitInstance.kakaoMapService
}
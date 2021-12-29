package com.example.a2021hackthon.di.module

import com.example.a2021hackthon.model.remote.dao.EmotionService
import com.example.a2021hackthon.model.remote.dao.KakaoMapService
import com.example.a2021hackthon.model.remote.dao.SurveyService
import com.example.a2021hackthon.model.remote.dao.TimeService
import com.example.a2021hackthon.model.repository.EmotionRepository
import com.example.a2021hackthon.model.repository.KakaoMapRepository
import com.example.a2021hackthon.model.repository.SurveyRepository
import com.example.a2021hackthon.model.repository.TimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.annotation.Signed
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideEmotionRepository(service: EmotionService): EmotionRepository =
        EmotionRepository(service)

    @Singleton
    @Provides
    fun provideSurveyRepository(service: SurveyService): SurveyRepository =
        SurveyRepository(service)

    @Singleton
    @Provides
    fun provideTimeRepository(service: TimeService): TimeRepository =
        TimeRepository(service)

    @Singleton
    @Provides
    fun provideKakaoMapRepository(service: KakaoMapService): KakaoMapRepository =
        KakaoMapRepository(service)
}
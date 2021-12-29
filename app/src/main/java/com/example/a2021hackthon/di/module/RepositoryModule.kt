package com.example.a2021hackthon.di.module

import com.example.a2021hackthon.model.remote.dao.EmotionService
import com.example.a2021hackthon.model.repository.EmotionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideEmotionRepository(service: EmotionService): EmotionRepository =
        EmotionRepository(service)
}
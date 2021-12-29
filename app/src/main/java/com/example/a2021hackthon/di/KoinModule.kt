package com.example.a2021hackthon.di

import com.example.a2021hackthon.model.remote.RetrofitInstance
import com.example.a2021hackthon.model.remote.dao.EmotionService
import com.example.a2021hackthon.model.repository.EmotionRepository
import com.example.a2021hackthon.viewmodel.AnalyzeEmotionViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val repoModule = module {
    single {
        EmotionRepository(RetrofitInstance.emotionService)
    }
}

val viewModelModule = module {
    viewModel {
        AnalyzeEmotionViewModel(get())
    }
}
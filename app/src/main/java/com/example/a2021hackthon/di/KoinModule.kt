package com.example.a2021hackthon.di

import com.example.a2021hackthon.model.remote.RetrofitInstance
import com.example.a2021hackthon.model.repository.EmotionRepository
import com.example.a2021hackthon.viewmodel.AnalyzePhotoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoModule = module {
    single {
        EmotionRepository(RetrofitInstance.emotionService)
    }
}

val viewModelModule = module {
    viewModel {
        AnalyzePhotoViewModel(get())
    }
}
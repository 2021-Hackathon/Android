package com.example.a2021hackthon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2021hackthon.model.remote.dto.Food
import com.example.a2021hackthon.model.remote.dto.Survey
import com.example.a2021hackthon.model.repository.SurveyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor (private val repo: SurveyRepository) : ViewModel() {
    val isSuccess = MutableLiveData<Food>()
    val isFailure = MutableLiveData<String>()

    fun postAnalyzeSurvey(survey: Survey) {
        repo.postAnalyzeSurvey(survey).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isSuccess.postValue(it)
            }, {
                isFailure.postValue(it.message)
            })
    }
}
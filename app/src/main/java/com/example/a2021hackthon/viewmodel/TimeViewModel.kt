package com.example.a2021hackthon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2021hackthon.model.remote.dto.Food
import com.example.a2021hackthon.model.repository.TimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class TimeViewModel @Inject constructor(private val repository: TimeRepository): ViewModel() {
    val isSuccess = MutableLiveData<Food>()
    val isFailure = MutableLiveData<String>()

    fun getAnalyzeTime() {
        repository.getAnalyzeTime().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            Log.d("success", it.toString())
            isSuccess.postValue(it)
        }, {
            Log.d("failure", it.message.toString())
            isFailure.postValue(it.message)
        })
    }
}
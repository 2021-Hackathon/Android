package com.example.a2021hackthon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2021hackthon.model.remote.dto.Place
import com.example.a2021hackthon.model.repository.KakaoMapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class KaKaoMapViewModel @Inject constructor(
    private val repository: KakaoMapRepository
): ViewModel() {

    val isSuccess = MutableLiveData<List<Place>>()
    val isFailure = MutableLiveData<String>()

    fun getSearchPlace(keyword: String) {
        repository.getSearchPlace(keyword)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("getSearchPlace", it.toString())
                isSuccess.postValue(it.documents)
            }, {
                Log.e("getSearchPlace", it.message.toString())
                isFailure.postValue(it.message)
            })
    }
}
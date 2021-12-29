package com.example.a2021hackthon.viewmodel

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2021hackthon.model.remote.dto.Food
import com.example.a2021hackthon.model.repository.EmotionRepository
import com.example.a2021hackthon.view.utils.toImageBody
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AnalyzePhotoViewModel(
    private val repository: EmotionRepository
) : ViewModel() {

    val isSuccess = MutableLiveData<Food>()
    val isFailure = MutableLiveData<String>()

    fun postAnalyzePhoto(contentResolver: ContentResolver, image: Uri) {
        val imageBody = image.toImageBody("attachment", contentResolver)

        repository.postAnalyzeEmotion(imageBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("asdfsdf", it.toString())
                isSuccess.value = it
            }, {
                isFailure.value = it.message
            })
    }
}
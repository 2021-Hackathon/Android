package com.example.a2021hackthon.viewmodel

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2021hackthon.model.remote.dto.EmotionFood
import com.example.a2021hackthon.model.remote.dto.Food
import com.example.a2021hackthon.model.repository.EmotionRepository
import com.example.a2021hackthon.view.utils.toImageBody
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AnalyzePhotoViewModel @Inject constructor(
    private val repository: EmotionRepository
) : ViewModel() {

    val isSuccess = MutableLiveData<EmotionFood>()
    val isFailure = MutableLiveData<String>()

    fun postAnalyzePhoto(context: Context, image: Uri) {
        val imageBody = image.toImageBody("attachment", context)

        repository.postAnalyzeEmotion(imageBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("postAnalyzePhoto", it.toString())
                isSuccess.value = it
            }, {
                Log.e("postAnalyzePhoto", it.message.toString())
                isFailure.value = it.message
            })
    }
}
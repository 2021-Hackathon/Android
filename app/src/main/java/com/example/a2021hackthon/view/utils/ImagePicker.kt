package com.example.a2021hackthon.view.utils

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.MutableLiveData

object ImagePicker {
    val imageList = MutableLiveData<List<Uri>>()

    fun selectStart(resultLauncher: ActivityResultLauncher<Intent>) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        resultLauncher.launch(Intent.createChooser(intent, "Select Picture"))
    }

    fun init(it: ActivityResult) {
        if (it.resultCode == Activity.RESULT_OK) {
            try {
                val list = mutableListOf<Uri>()

                if (it.data != null)
                    list.add(it.data!!.data!!)

                imageList.value = list
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getBitmap(contextResolver: ContentResolver, uri: Uri): Bitmap =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(contextResolver, uri)
            ImageDecoder.decodeBitmap(source)
        } else {
            MediaStore.Images.Media.getBitmap(contextResolver, uri)
        }
}
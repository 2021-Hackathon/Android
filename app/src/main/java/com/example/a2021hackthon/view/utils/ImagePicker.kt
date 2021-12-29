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
import java.io.ByteArrayOutputStream
import java.util.*

class ImagePicker {
    val image = MutableLiveData<Uri>()
    val cameraImage = MutableLiveData<Uri>()

    fun selectStart(resultLauncher: ActivityResultLauncher<Intent>) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        resultLauncher.launch(Intent.createChooser(intent, "Select Picture"))
    }

    fun cameraStart(resultLauncher: ActivityResultLauncher<Intent>) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(intent)
    }

    fun initSelection(it: ActivityResult) {
        if (it.resultCode == Activity.RESULT_OK) {
            try {
                if (it.data != null)
                    image.value = it.data!!.data!!
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun initCamera(it: ActivityResult, contentResolver: ContentResolver) {
        if (it.resultCode == Activity.RESULT_OK) {
            if (it.data != null) {
                val bundle = it.data!!.extras!!
                val bitmap = bundle.get("data") as Bitmap?
                cameraImage.value = getImageUri(contentResolver, bitmap!!)
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

    private fun getImageUri(contextResolver: ContentResolver, bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()

        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        }
        val path = MediaStore.Images.Media.insertImage(
            contextResolver, bitmap, "Title" + " - " + Calendar.getInstance().time, null)
        return Uri.parse(path)
    }
}
package com.example.a2021hackthon.view.utils

import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun Uri.toImageBody(name: String, contentResolver: ContentResolver): MultipartBody.Part {
    val projection = arrayOf(MediaStore.Images.Media._ID)
    val cursor = contentResolver.query(this, projection, null, null, null)!!
    cursor.moveToNext()

    val idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
    val path = cursor.getString(idx)

    Log.e("path" , path)

    val imageFile = File(path)

    cursor.close()

    return MultipartBody.Part.createFormData(
        name, imageFile.name, imageFile.asRequestBody("image/*".toMediaTypeOrNull())
    )
}
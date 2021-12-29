package com.example.a2021hackthon.view.utils

import android.content.ContentResolver
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun Uri.toImageBody(name: String, contentResolver: ContentResolver): MultipartBody.Part {
    val cursor = contentResolver.query(this, null, null, null, null, null)!!

    cursor.moveToNext()

    val idx = cursor.getColumnIndex("_data")
    val path = cursor.getString(idx)
    cursor.close()

    val imageFile = File(path)

    return MultipartBody.Part.createFormData(
        name, imageFile.name, imageFile.asRequestBody("image/*".toMediaTypeOrNull())
    )
}
package com.example.a2021hackthon.view.utils

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import android.provider.OpenableColumns
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.Exception
import kotlin.io.path.createTempDirectory


fun Uri.toImageBody(name: String, context: Context): MultipartBody.Part {
//    val projection = arrayOf(MediaStore.Images.Media._ID)
//    val cursor = contentResolver.query(this, projection, null, null, null)!!
//    cursor.moveToNext()
//
//    val idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
//    Log.d("toimagebody", "idx: $idx, uri: $this")
//    val path = cursor.getString(idx)
//
//    val imageFile = File(path)
//
//    cursor.close()
//
//    return MultipartBody.Part.createFormData(
//        name, imageFile.name, imageFile.asRequestBody("image/*".toMediaTypeOrNull())
//    )

    val file = getFile(context, this)!!
    return MultipartBody.Part.createFormData(
        name, file.name, file.asRequestBody("image/*".toMediaTypeOrNull())
    )
}

fun getFile(context: Context, uri: Uri): File? {
    val fileName =
        File(context.filesDir.path + File.separatorChar + queryName(context, uri))

    try {
        context.contentResolver.openInputStream(uri).apply {
            createFileFromStream(this!!, fileName)
        }
    } catch (ex: Exception) {
        Log.e("Save File", ex.message!!)
        ex.printStackTrace()
    }

    return fileName
}

private fun createFileFromStream(ins: InputStream, destination: File?) {
    try {
        FileOutputStream(destination).use { os ->
            val buffer = ByteArray(4096)
            var length: Int

            while (ins.read(buffer).also { length = it } > 0) {
                os.write(buffer, 0, length)
            }

            os.flush()
        }
    } catch (ex: Exception) {
        Log.e("Save File", ex.message!!)
        ex.printStackTrace()
    }
}

private fun queryName(context: Context, uri: Uri): String {
    val returnCursor: Cursor =
        context.contentResolver.query(uri, null, null, null, null)!!
    val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    returnCursor.moveToFirst()
    val name = returnCursor.getString(nameIndex)
    returnCursor.close()
    return name
}
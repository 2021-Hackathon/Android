package com.example.a2021hackthon.model.remote

import android.util.Log
import com.example.a2021hackthon.model.remote.dto.Res
import io.reactivex.rxjava3.functions.Function
import org.json.JSONObject
import retrofit2.Response

abstract class BaseRemote<SV> {
    abstract val service: SV

    protected fun <T> getResponse(): Function<Response<Res<T>>, T> {
        return Function {
            checkError(it)
            it.body()!!.data
        }
    }

    protected fun <T> getKakaoResponse(): Function<Response<T>, T> {
        return Function {
            checkError(it)
            it.body()!!
        }
    }

    private fun <T> checkError(response: Response<T>) {
        Log.d("chekcError", response.raw().toString())
        if (!response.isSuccessful) {
            val errorBody = JSONObject(response.errorBody()!!.string())
            throw Throwable(errorBody.getString("message"))
        }
    }
}
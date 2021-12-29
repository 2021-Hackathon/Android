package com.example.a2021hackthon.model.remote

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

    private fun <T> checkError(response: Response<T>) {
        if (!response.isSuccessful) {
            val errorBody = JSONObject(response.errorBody()!!.string())
            throw Throwable(errorBody.getString("message"))
        }
    }
}
package com.example.a2021hackthon.model.repository

import com.example.a2021hackthon.model.remote.BaseRemote
import com.example.a2021hackthon.model.remote.dao.KakaoMapService
import com.example.a2021hackthon.model.remote.dto.Documents
import com.example.a2021hackthon.model.remote.dto.Place
import io.reactivex.rxjava3.core.Single

class KakaoMapRepository(override val service: KakaoMapService) : BaseRemote<KakaoMapService>() {
    fun getSearchPlace(keyword: String): Single<Documents> =
        service.getSearchPlace(keyword).map(getKakaoResponse())
}
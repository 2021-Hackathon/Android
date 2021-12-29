package com.example.a2021hackthon.model.remote.dto

data class Res<T>(
    val status: Int,
    val msg: String,
    val data: T
)
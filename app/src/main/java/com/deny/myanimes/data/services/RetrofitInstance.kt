package com.deny.myanimes.data.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    private val BASE_URL = "https://api.jikan.moe/v4/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val animesService: AnimesServices by lazy {
        retrofit.create(AnimesServices::class.java)
    }
}
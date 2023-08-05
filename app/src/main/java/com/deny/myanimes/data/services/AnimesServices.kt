package com.deny.myanimes.data.services

import com.deny.myanimes.data.models.AnimeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AnimesServices {

    @Headers("Content-Type: application/json")
    @GET("anime")
    fun getAnimesByName(
        @Query("q") query: String,
        @Query("sfw") safeForWork: Boolean = true
    ): Call<AnimeModel>

    @Headers("Content-Type: application/json")
    @GET("anime")
    fun getAllAnimes(
        @Query("page") page: Int
    ): Call<AnimeModel>
}
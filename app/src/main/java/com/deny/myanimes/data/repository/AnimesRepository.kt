package com.deny.myanimes.data.repository

import com.deny.myanimes.data.models.AnimeModel
import com.deny.myanimes.data.services.RetrofitInstance
import retrofit2.Call

class AnimesRepository {
    private val animesServices = RetrofitInstance.animesService

    suspend fun getAnimesByName(query: String): Call<AnimeModel> {
        return animesServices.getAnimesByName(query = query)
    }

    suspend fun getAnimes(query: String): Call<AnimeModel> {
        //return animesServices.getAnimesByName(query = query)
        return animesServices.getAllAnimes(query.toInt())
    }
}
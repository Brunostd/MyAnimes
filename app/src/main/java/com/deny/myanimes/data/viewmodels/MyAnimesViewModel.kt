package com.deny.myanimes.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deny.myanimes.data.models.AnimeModel
import com.deny.myanimes.data.repository.AnimesRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MyAnimesViewModel: ViewModel() {

    private val reposiroty = AnimesRepository()

    private val _animes = MutableLiveData<AnimeModel>()
    val animes: LiveData<AnimeModel> = _animes

    fun getAnimes(query: String) {
        viewModelScope.launch {
            try {
                val auxAnimes = reposiroty.getAnimes(query = query)
                auxAnimes.enqueue(object : Callback<AnimeModel> {
                    override fun onResponse(
                        call: Call<AnimeModel>,
                        response: Response<AnimeModel>
                    ) {
                        _animes.postValue(response.body())
                    }

                    override fun onFailure(call: Call<AnimeModel>, t: Throwable) {
                        println("error")
                    }

                })
            } catch (e: Exception) {
                println("error"+ e.message)
            }
        }
    }

    fun getAnimesByName(query: String) {
        viewModelScope.launch {
            try {
                val auxAnimes = reposiroty.getAnimesByName(query = query)
                auxAnimes.enqueue(object : Callback<AnimeModel> {
                    override fun onResponse(
                        call: Call<AnimeModel>,
                        response: Response<AnimeModel>
                    ) {
                        _animes.postValue(response.body())
                    }

                    override fun onFailure(call: Call<AnimeModel>, t: Throwable) {
                        println("error")
                    }

                })
            } catch (e: Exception) {
                println("error"+ e.message)
            }
        }
    }
}
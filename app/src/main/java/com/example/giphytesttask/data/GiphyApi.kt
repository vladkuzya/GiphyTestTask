package com.example.giphytesttask.data

import com.example.giphytesttask.model.GetGifsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("search")
    suspend fun getGifsByQuery(@Query("q") query: String): Response<GetGifsResponse>
}
package com.example.giphytesttask.data.repository

import com.example.giphytesttask.data.GiphyApi
import com.example.giphytesttask.model.GetGifsResponse
import com.example.giphytesttask.model.Resource
import com.example.giphytesttask.util.Constants
import com.example.giphytesttask.util.Network
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class GiphyRepository @Inject constructor(
    private val giphyApi: GiphyApi,
    private val network: Network
) {

    suspend fun search(query: String): Resource<GetGifsResponse> {
        return when (val response = processCall { (giphyApi::getGifsByQuery)(query) }) {
            is GetGifsResponse -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!network.isConnected()) {
            return Constants.NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            Constants.NETWORK_ERROR
        }
    }
}
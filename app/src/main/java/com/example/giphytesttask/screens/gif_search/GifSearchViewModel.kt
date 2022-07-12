package com.example.giphytesttask.screens.gif_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphytesttask.data.repository.GiphyRepository
import com.example.giphytesttask.model.GetGifsResponse
import com.example.giphytesttask.model.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class GifSearchViewModel @Inject constructor(
    private val giphyRepository: GiphyRepository
) : ViewModel() {
    private val _gifsLiveData = MutableLiveData<Resource<GetGifsResponse>>()
    val gifsLiveData: LiveData<Resource<GetGifsResponse>> get() = _gifsLiveData

    fun search(query: String) {
        viewModelScope.launch {
            _gifsLiveData.value = Resource.Loading()
            _gifsLiveData.value = giphyRepository.search(query)
        }
    }
}
package com.pathakbau.musicwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pathakbau.musicwiki.data.topGenres.TopTagsResponse
import com.pathakbau.musicwiki.repository.MusicRepository
import com.pathakbau.musicwiki.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

enum class FirstScreenModes {
    COLLAPSED, EXPANDED
}

class MusicViewModel: ViewModel() {

    private val musicRepository = MusicRepository()
    val topGenres: MutableLiveData<Resource<TopTagsResponse>> = MutableLiveData()
    var firstScreenMode: MutableLiveData<FirstScreenModes> = MutableLiveData(FirstScreenModes.COLLAPSED)

    init {
        getTopGenres()
    }

    fun getTopGenres() = viewModelScope.launch {
        topGenres.postValue(Resource.Loading())
        val response = musicRepository.getTopGenres()
        topGenres.postValue(handleTopGenresResponse(response))
    }

    private fun handleTopGenresResponse(response: Response<TopTagsResponse>) : Resource<TopTagsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}
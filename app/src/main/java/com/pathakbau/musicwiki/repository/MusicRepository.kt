package com.pathakbau.musicwiki.repository

import com.pathakbau.musicwiki.api.RetrofitInstance

class MusicRepository {

    suspend fun getTopGenres() =
        RetrofitInstance.musicApi.getTopGenres()

}
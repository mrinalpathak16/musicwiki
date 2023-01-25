package com.pathakbau.musicwiki.api

import com.pathakbau.musicwiki.util.JSON
import com.pathakbau.musicwiki.util.LAST_FM_API_KEY
import com.pathakbau.musicwiki.util.TOP_TAGS_METHOD
import com.pathakbau.musicwiki.data.topGenres.TopTagsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicAPI {

    @GET("2.0/")
    suspend fun getTopGenres(
        @Query("api_key")
        apiKey:String = LAST_FM_API_KEY,
        @Query("method")
        method:String = TOP_TAGS_METHOD,
        @Query("format")
        format:String = JSON
    ): Response<TopTagsResponse>

}
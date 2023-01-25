package com.pathakbau.musicwiki.data.topGenres


import com.google.gson.annotations.SerializedName

data class TopTagsResponse(
    @SerializedName("toptags")
    val topTags: TopTags
)
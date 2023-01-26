package com.pathakbau.musicwiki.data.genre


import com.google.gson.annotations.SerializedName

data class Wiki(
    @SerializedName("content")
    val content: String,
    @SerializedName("summary")
    val summary: String
)
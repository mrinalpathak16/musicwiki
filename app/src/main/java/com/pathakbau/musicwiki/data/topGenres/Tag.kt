package com.pathakbau.musicwiki.data.topGenres


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("count")
    val count: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("reach")
    val reach: Int
)
package com.pathakbau.musicwiki.data.album


import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
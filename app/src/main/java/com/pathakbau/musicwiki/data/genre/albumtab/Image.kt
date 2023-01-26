package com.pathakbau.musicwiki.data.genre.albumtab


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("size")
    val size: String,
    @SerializedName("#text")
    val text: String
)
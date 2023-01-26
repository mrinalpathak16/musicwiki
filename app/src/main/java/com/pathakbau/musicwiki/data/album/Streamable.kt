package com.pathakbau.musicwiki.data.album


import com.google.gson.annotations.SerializedName

data class Streamable(
    @SerializedName("fulltrack")
    val fulltrack: String,
    @SerializedName("#text")
    val text: String
)
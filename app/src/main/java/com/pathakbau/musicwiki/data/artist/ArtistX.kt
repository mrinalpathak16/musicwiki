package com.pathakbau.musicwiki.data.artist


import com.google.gson.annotations.SerializedName

data class ArtistX(
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
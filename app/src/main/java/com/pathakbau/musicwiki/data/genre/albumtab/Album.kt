package com.pathakbau.musicwiki.data.genre.albumtab


import com.google.gson.annotations.SerializedName
import com.pathakbau.musicwiki.data.Image

data class Album(
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
package com.pathakbau.musicwiki.data.genre.trackstab


import com.google.gson.annotations.SerializedName
import com.pathakbau.musicwiki.data.Image

data class Track(
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
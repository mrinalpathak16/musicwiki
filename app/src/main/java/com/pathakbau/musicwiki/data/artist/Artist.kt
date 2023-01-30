package com.pathakbau.musicwiki.data.artist


import com.google.gson.annotations.SerializedName
import com.pathakbau.musicwiki.data.Image

data class Artist(
    @SerializedName("bio")
    val bio: Bio,
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("stats")
    val stats: Stats,
    @SerializedName("tags")
    val tags: Tags,
    @SerializedName("url")
    val url: String
)
package com.pathakbau.musicwiki.data.artist


import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("bio")
    val bio: Bio,
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("ontour")
    val ontour: String,
    @SerializedName("similar")
    val similar: Similar,
    @SerializedName("stats")
    val stats: Stats,
    @SerializedName("streamable")
    val streamable: String,
    @SerializedName("tags")
    val tags: Tags,
    @SerializedName("url")
    val url: String
)
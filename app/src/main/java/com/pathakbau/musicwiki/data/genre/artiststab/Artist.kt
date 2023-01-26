package com.pathakbau.musicwiki.data.genre.artiststab


import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("@attr")
    val attr: Attr,
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("streamable")
    val streamable: String,
    @SerializedName("url")
    val url: String
)
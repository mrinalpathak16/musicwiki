package com.pathakbau.musicwiki.data.artist.topTracks


import com.google.gson.annotations.SerializedName
import com.pathakbau.musicwiki.data.genre.TabListItem

data class Track(
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("@attr")
    val attr: AttrX,
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("playcount")
    val playcount: String,
    @SerializedName("streamable")
    val streamable: String,
    @SerializedName("url")
    val url: String
)
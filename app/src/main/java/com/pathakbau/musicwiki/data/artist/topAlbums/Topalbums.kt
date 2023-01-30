package com.pathakbau.musicwiki.data.artist.topAlbums


import com.google.gson.annotations.SerializedName

data class Topalbums(
    @SerializedName("album")
    val album: List<Album>,
    @SerializedName("@attr")
    val attr: Attr
)
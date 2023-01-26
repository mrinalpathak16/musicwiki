package com.pathakbau.musicwiki.data.genre.albumtab


import com.google.gson.annotations.SerializedName

data class Albums(
    @SerializedName("album")
    val album: List<Album>,
    @SerializedName("@attr")
    val attr: AttrX
)
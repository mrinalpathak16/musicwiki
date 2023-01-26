package com.pathakbau.musicwiki.data.genre.artiststab


import com.google.gson.annotations.SerializedName

data class Topartists(
    @SerializedName("artist")
    val artist: List<Artist>,
    @SerializedName("@attr")
    val attr: AttrX
)
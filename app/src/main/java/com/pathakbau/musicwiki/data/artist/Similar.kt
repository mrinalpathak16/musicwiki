package com.pathakbau.musicwiki.data.artist


import com.google.gson.annotations.SerializedName

data class Similar(
    @SerializedName("artist")
    val artist: List<ArtistX>
)
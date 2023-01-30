package com.pathakbau.musicwiki.data.genre.trackstab


import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("track")
    val track: List<Track>
)
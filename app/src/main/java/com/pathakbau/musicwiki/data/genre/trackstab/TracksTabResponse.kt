package com.pathakbau.musicwiki.data.genre.trackstab


import com.google.gson.annotations.SerializedName

data class TracksTabResponse(
    @SerializedName("tracks")
    val tracks: Tracks
)
package com.pathakbau.musicwiki.data.artist.topTracks


import com.google.gson.annotations.SerializedName

data class Toptracks(
    @SerializedName("track")
    val track: List<Track>
)
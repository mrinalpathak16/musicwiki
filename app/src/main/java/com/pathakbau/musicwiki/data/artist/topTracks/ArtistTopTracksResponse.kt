package com.pathakbau.musicwiki.data.artist.topTracks


import com.google.gson.annotations.SerializedName

data class ArtistTopTracksResponse(
    @SerializedName("toptracks")
    val toptracks: Toptracks
)
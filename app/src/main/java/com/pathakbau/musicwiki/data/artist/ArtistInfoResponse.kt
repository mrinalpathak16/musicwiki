package com.pathakbau.musicwiki.data.artist


import com.google.gson.annotations.SerializedName

data class ArtistInfoResponse(
    @SerializedName("artist")
    val artist: Artist
)
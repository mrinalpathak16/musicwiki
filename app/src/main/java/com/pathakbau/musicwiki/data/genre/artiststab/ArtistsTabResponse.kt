package com.pathakbau.musicwiki.data.genre.artiststab


import com.google.gson.annotations.SerializedName

data class ArtistsTabResponse(
    @SerializedName("topartists")
    val topartists: Topartists
)
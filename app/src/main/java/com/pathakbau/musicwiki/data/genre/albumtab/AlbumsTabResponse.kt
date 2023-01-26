package com.pathakbau.musicwiki.data.genre.albumtab


import com.google.gson.annotations.SerializedName

data class AlbumsTabResponse(
    @SerializedName("albums")
    val albums: Albums
)
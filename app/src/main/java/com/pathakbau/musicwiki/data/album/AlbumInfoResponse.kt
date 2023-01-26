package com.pathakbau.musicwiki.data.album


import com.google.gson.annotations.SerializedName

data class AlbumInfoResponse(
    @SerializedName("album")
    val album: Album
)
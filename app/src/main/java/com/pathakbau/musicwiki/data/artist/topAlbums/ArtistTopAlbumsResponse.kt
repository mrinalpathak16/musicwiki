package com.pathakbau.musicwiki.data.artist.topAlbums


import com.google.gson.annotations.SerializedName

data class ArtistTopAlbumsResponse(
    @SerializedName("topalbums")
    val topalbums: Topalbums
)
package com.pathakbau.musicwiki.data.artist


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("link")
    val link: Link
)
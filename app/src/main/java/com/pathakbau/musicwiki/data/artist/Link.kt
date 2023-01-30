package com.pathakbau.musicwiki.data.artist


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("href")
    val href: String,
    @SerializedName("rel")
    val rel: String,
    @SerializedName("#text")
    val text: String
)
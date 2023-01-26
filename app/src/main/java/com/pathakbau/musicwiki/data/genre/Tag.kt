package com.pathakbau.musicwiki.data.genre


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("name")
    val name: String,
    @SerializedName("reach")
    val reach: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("wiki")
    val wiki: Wiki
)
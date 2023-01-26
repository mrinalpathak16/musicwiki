package com.pathakbau.musicwiki.data.genre.trackstab


import com.google.gson.annotations.SerializedName

data class Attr(
    @SerializedName("page")
    val page: String,
    @SerializedName("perPage")
    val perPage: String,
    @SerializedName("tag")
    val tag: String,
    @SerializedName("total")
    val total: String,
    @SerializedName("totalPages")
    val totalPages: String
)
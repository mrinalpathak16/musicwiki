package com.pathakbau.musicwiki.data.genre


import com.google.gson.annotations.SerializedName

data class TagInfoResponse(
    @SerializedName("tag")
    val tag: Tag
)
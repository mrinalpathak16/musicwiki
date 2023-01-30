package com.pathakbau.musicwiki.data.artist


import com.google.gson.annotations.SerializedName
import com.pathakbau.musicwiki.data.topGenres.Tag

data class Tags(
    @SerializedName("tag")
    val tag: List<Tag>
)
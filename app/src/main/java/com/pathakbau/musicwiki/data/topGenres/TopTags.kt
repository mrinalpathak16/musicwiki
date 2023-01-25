package com.pathakbau.musicwiki.data.topGenres


import com.google.gson.annotations.SerializedName

data class TopTags(
    @SerializedName("@attr")
    val attr: Attr,
    @SerializedName("tag")
    val tags: List<Tag>
)
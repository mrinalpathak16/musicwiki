package com.pathakbau.musicwiki.data.artist

import com.pathakbau.musicwiki.data.Image

data class TopListItem(
    val name: String,
    val artistName: String,
    val image: List<Image>
)

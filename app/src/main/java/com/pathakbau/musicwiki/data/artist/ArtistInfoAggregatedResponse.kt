package com.pathakbau.musicwiki.data.artist

data class ArtistInfoAggregatedResponse(
    val artistInfoResponse: ArtistInfoResponse,
    val artistTopTracksResponse: List<TopListItem>,
    val artistTopAlbumsResponse: List<TopListItem>
)

package com.pathakbau.musicwiki.data.artist

import com.pathakbau.musicwiki.data.artist.topAlbums.ArtistTopAlbumsResponse
import com.pathakbau.musicwiki.data.artist.topTracks.ArtistTopTracksResponse
import com.pathakbau.musicwiki.data.genre.TabListItem

data class ArtistInfoAggregatedResponse(
    val artistInfoResponse: ArtistInfoResponse,
    val artistTopTracksResponse: List<TopListItem>,
    val artistTopAlbumsResponse: List<TopListItem>
)

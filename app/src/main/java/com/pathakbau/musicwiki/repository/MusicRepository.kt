package com.pathakbau.musicwiki.repository

import com.pathakbau.musicwiki.api.RetrofitInstance
import com.pathakbau.musicwiki.data.genre.TagInfoResponse
import com.pathakbau.musicwiki.data.genre.albumtab.AlbumsTabResponse
import com.pathakbau.musicwiki.data.genre.artiststab.ArtistsTabResponse
import com.pathakbau.musicwiki.data.genre.trackstab.TracksTabResponse
import com.pathakbau.musicwiki.data.topGenres.TopTagsResponse
import retrofit2.Response

class MusicRepository {

    suspend fun getTopGenres(): Response<TopTagsResponse> =
        RetrofitInstance.musicApi.getTopGenres()

    suspend fun getGenreInfo(genreName: String): Response<TagInfoResponse> =
        RetrofitInstance.musicApi.getGenreInfo(genreName)

    suspend fun getGenreTabAlbums(genreName: String): Response<AlbumsTabResponse> =
        RetrofitInstance.musicApi.getGenreTabAlbums(genreName)

    suspend fun getGenreTabArtists(genreName: String): Response<ArtistsTabResponse> =
        RetrofitInstance.musicApi.getGenreTabArtists(genreName)

    suspend fun getGenreTabTracks(genreName: String): Response<TracksTabResponse> =
        RetrofitInstance.musicApi.getGenreTabTracks(genreName)

}
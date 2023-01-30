package com.pathakbau.musicwiki.repository

import com.pathakbau.musicwiki.api.RetrofitInstance
import com.pathakbau.musicwiki.data.album.AlbumInfoResponse
import com.pathakbau.musicwiki.data.artist.ArtistInfoResponse
import com.pathakbau.musicwiki.data.artist.topAlbums.ArtistTopAlbumsResponse
import com.pathakbau.musicwiki.data.artist.topTracks.ArtistTopTracksResponse
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

    suspend fun getAlbumInfo(albumName: String, artistName: String): Response<AlbumInfoResponse> =
        RetrofitInstance.musicApi.getAlbumInfo(albumName, artistName)

    suspend fun getArtistInfo(artistName: String): Response<ArtistInfoResponse> =
        RetrofitInstance.musicApi.getArtistInfo(artistName)

    suspend fun getArtistTopTracks(artistName: String): Response<ArtistTopTracksResponse> =
        RetrofitInstance.musicApi.getArtistTopTracks(artistName)

    suspend fun getArtistTopAlbums(artistName: String): Response<ArtistTopAlbumsResponse> =
        RetrofitInstance.musicApi.getArtistTopAlbums(artistName)

}
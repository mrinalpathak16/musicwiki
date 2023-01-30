package com.pathakbau.musicwiki.api

import com.pathakbau.musicwiki.data.album.AlbumInfoResponse
import com.pathakbau.musicwiki.data.artist.ArtistInfoResponse
import com.pathakbau.musicwiki.data.artist.topAlbums.ArtistTopAlbumsResponse
import com.pathakbau.musicwiki.data.artist.topTracks.ArtistTopTracksResponse
import com.pathakbau.musicwiki.data.genre.TagInfoResponse
import com.pathakbau.musicwiki.data.genre.albumtab.AlbumsTabResponse
import com.pathakbau.musicwiki.data.genre.artiststab.ArtistsTabResponse
import com.pathakbau.musicwiki.data.genre.trackstab.TracksTabResponse
import com.pathakbau.musicwiki.data.topGenres.TopTagsResponse
import com.pathakbau.musicwiki.util.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicAPI {

    @GET(".")
    suspend fun getTopGenres(
        @Query("api_key")
        apiKey:String = LAST_FM_API_KEY,
        @Query("method")
        method:String = TOP_TAGS_METHOD,
        @Query("format")
        format:String = JSON
    ): Response<TopTagsResponse>

    @GET(".")
    suspend fun getGenreInfo(
        @Query("tag")
        genreName: String,
        @Query("api_key")
        apiKey:String = LAST_FM_API_KEY,
        @Query("method")
        method:String = TAG_GET_INFO_METHOD,
        @Query("format")
        format:String = JSON
    ): Response<TagInfoResponse>

    @GET(".")
    suspend fun getGenreTabAlbums(
        @Query("tag")
        genreName: String,
        @Query("api_key")
        apiKey: String = LAST_FM_API_KEY,
        @Query("method")
        method:String = TAG_GET_TOP_ALBUMS_METHOD,
        @Query("format")
        format:String = JSON
    ): Response<AlbumsTabResponse>

    @GET(".")
    suspend fun getGenreTabArtists(
        @Query("tag")
        genreName: String,
        @Query("api_key")
        apiKey: String = LAST_FM_API_KEY,
        @Query("method")
        method:String = TAG_GET_TOP_ARTISTS_METHOD,
        @Query("format")
        format:String = JSON
    ): Response<ArtistsTabResponse>

    @GET(".")
    suspend fun getGenreTabTracks(
        @Query("tag")
        genreName: String,
        @Query("api_key")
        apiKey: String = LAST_FM_API_KEY,
        @Query("method")
        method:String = TAG_GET_TOP_TRACKS_METHOD,
        @Query("format")
        format:String = JSON
    ): Response<TracksTabResponse>

    @GET(".")
    suspend fun getAlbumInfo(
        @Query("album")
        albumName: String,
        @Query("artist")
        artistName: String,
        @Query("api_key")
        apiKey: String = LAST_FM_API_KEY,
        @Query("method")
        method:String = ALBUM_GET_INFO,
        @Query("format")
        format:String = JSON
    ): Response<AlbumInfoResponse>

    @GET(".")
    suspend fun getArtistInfo(
        @Query("artist")
        artistName: String,
        @Query("api_key")
        apiKey: String = LAST_FM_API_KEY,
        @Query("method")
        method:String = ARTIST_GET_INFO,
        @Query("format")
        format:String = JSON
    ): Response<ArtistInfoResponse>

    @GET(".")
    suspend fun getArtistTopTracks(
        @Query("artist")
        artistName: String,
        @Query("api_key")
        apiKey: String = LAST_FM_API_KEY,
        @Query("method")
        method:String = ARTIST_GET_TOP_TRACKS,
        @Query("format")
        format:String = JSON
    ): Response<ArtistTopTracksResponse>

    @GET(".")
    suspend fun getArtistTopAlbums(
        @Query("artist")
        artistName: String,
        @Query("api_key")
        apiKey: String = LAST_FM_API_KEY,
        @Query("method")
        method:String = ARTIST_GET_TOP_ALBUMS,
        @Query("format")
        format:String = JSON
    ): Response<ArtistTopAlbumsResponse>

}
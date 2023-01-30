package com.pathakbau.musicwiki.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pathakbau.musicwiki.data.album.AlbumInfoResponse
import com.pathakbau.musicwiki.data.artist.ArtistInfoAggregatedResponse
import com.pathakbau.musicwiki.data.artist.ArtistInfoResponse
import com.pathakbau.musicwiki.data.artist.TopListItem
import com.pathakbau.musicwiki.data.artist.topAlbums.ArtistTopAlbumsResponse
import com.pathakbau.musicwiki.data.artist.topTracks.ArtistTopTracksResponse
import com.pathakbau.musicwiki.data.genre.TabListItem
import com.pathakbau.musicwiki.data.genre.TagInfoResponse
import com.pathakbau.musicwiki.data.genre.albumtab.AlbumsTabResponse
import com.pathakbau.musicwiki.data.genre.artiststab.ArtistsTabResponse
import com.pathakbau.musicwiki.data.genre.trackstab.TracksTabResponse
import com.pathakbau.musicwiki.data.topGenres.TopTagsResponse
import com.pathakbau.musicwiki.repository.MusicRepository
import com.pathakbau.musicwiki.util.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response

private const val TAG = "MusicViewModel"

enum class FirstScreenModes {
    COLLAPSED, EXPANDED
}

class MusicViewModel: ViewModel() {

    private val musicRepository = MusicRepository()

    val topGenres: MutableLiveData<Resource<TopTagsResponse>> = MutableLiveData()
    var firstScreenMode: MutableLiveData<FirstScreenModes> = MutableLiveData(FirstScreenModes.COLLAPSED)

    val genreInfo: MutableLiveData<Resource<TagInfoResponse>> = MutableLiveData()
    val genreTabAlbums: MutableLiveData<Resource<List<TabListItem>>> = MutableLiveData()
    val genreTabArtists: MutableLiveData<Resource<List<TabListItem>>> = MutableLiveData()
    val genreTabTracks: MutableLiveData<Resource<List<TabListItem>>> = MutableLiveData()

    val albumInfo: MutableLiveData<Resource<AlbumInfoResponse>> = MutableLiveData()

    val artistInfo: MutableLiveData<Resource<ArtistInfoAggregatedResponse>> = MutableLiveData()

    init {
        requestTopGenres()
    }

    fun requestTopGenres() = viewModelScope.launch {
        topGenres.postValue(Resource.Loading())
        val response = musicRepository.getTopGenres()
        topGenres.postValue(handleResponse(response))
    }

    fun requestGenreInfo(genreName: String) = viewModelScope.launch {
        genreInfo.postValue(Resource.Loading())
        val genreInfoResponse = musicRepository.getGenreInfo(genreName)
        genreInfo.postValue((handleResponse(genreInfoResponse)))
    }

    fun requestGenreTabContents(genreName: String, tabType: Int) = when (tabType) {
        0 -> viewModelScope.launch {
            genreTabAlbums.postValue(Resource.Loading())
            val genreTabResponse = musicRepository.getGenreTabAlbums(genreName)
            val response = handleAlbumTabResponse(genreTabResponse)
            genreTabAlbums.postValue(response)
        }
        1 -> viewModelScope.launch {
            genreTabArtists.postValue(Resource.Loading())
            val genreTabResponse = musicRepository.getGenreTabArtists(genreName)
            val response = handleArtistTabResponse(genreTabResponse)
            genreTabArtists.postValue(response)
        }
        2 -> viewModelScope.launch {
            genreTabTracks.postValue(Resource.Loading())
            val genreTabResponse = musicRepository.getGenreTabTracks(genreName)
            val response = handleTrackTabResponse(genreTabResponse)
            genreTabTracks.postValue(response)
        }
        else -> throw RuntimeException("unexpected behaviour in tabs!!")
    }

    fun requestAlbumInfo(albumName: String, artistName: String) = viewModelScope.launch {
        albumInfo.postValue(Resource.Loading())
        val albumInfoResponse = musicRepository.getAlbumInfo(albumName, artistName)
        albumInfo.postValue(handleResponse(albumInfoResponse))
    }

    fun requestArtistInfo(artistName: String) = viewModelScope.launch {
        artistInfo.postValue(Resource.Loading())

        val x = async {
            musicRepository.getArtistInfo(artistName)
        }
        val y = async {
            musicRepository.getArtistTopTracks(artistName)
        }
        val z = async {
            musicRepository.getArtistTopAlbums(artistName)
        }
        val artistInfoResponse = x.await()
        val artistTopTracksResponse = y.await()
        val artistTopAlbumsResponse = z.await()

        artistInfo.postValue(handleArtistInfoResponse(
            artistInfoResponse,
            artistTopTracksResponse,
            artistTopAlbumsResponse
        ))
    }

    private fun <T> handleResponse(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleAlbumTabResponse(response: Response<AlbumsTabResponse>): Resource<List<TabListItem>> {
        if (response.isSuccessful) {
            response.body()?.let {
                val res =  it.albums.album.map { album ->
                    TabListItem(album.image, album.name,album.artist.name)
                }
                return Resource.Success(res)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleArtistTabResponse(response: Response<ArtistsTabResponse>):
            Resource<List<TabListItem>> {
        if (response.isSuccessful) {
            response.body()?.let {
                Log.d(TAG, "handleArtistTabResponse: ${it}")
                val res =  it.topartists.artist.map { artist ->
                    TabListItem(artist.image, artist.name)
                }
                return Resource.Success(res)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleTrackTabResponse(response: Response<TracksTabResponse>):
            Resource<List<TabListItem>> {
        if (response.isSuccessful) {
            response.body()?.let {
                val res =  it.tracks.track.map { track ->
                    TabListItem(track.image, track.name, track.artist.name)
                }
                return Resource.Success(res)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleArtistInfoResponse(
        artistInfoResponse: Response<ArtistInfoResponse>,
        artistTopTracksResponse: Response<ArtistTopTracksResponse>,
        artistTopAlbumsResponse: Response<ArtistTopAlbumsResponse>
    ): Resource<ArtistInfoAggregatedResponse> {
        if (
            artistInfoResponse.isSuccessful &&
                    artistTopTracksResponse.isSuccessful &&
                    artistTopAlbumsResponse.isSuccessful
        ) {
            artistInfoResponse.body()?.let { artistInfo ->
                artistTopTracksResponse.body()?.let { topTracks ->
                    artistTopAlbumsResponse.body()?.let { topAlbums ->
                        val topTracksList = topTracks.toptracks.track.map { track ->
                            TopListItem(track.name, track.artist.name, track.image)
                        }
                        val topAlbumsList = topAlbums.topalbums.album.map { album ->
                            TopListItem(album.name, album.artist.name, album.image)
                        }
                        return Resource.Success(
                            ArtistInfoAggregatedResponse(artistInfo, topTracksList, topAlbumsList)
                        )
                    }
                }
            }
        }
        return Resource.Error(artistInfoResponse.message())
    }
}
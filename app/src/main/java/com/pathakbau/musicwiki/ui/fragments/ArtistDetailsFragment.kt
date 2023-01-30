package com.pathakbau.musicwiki.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.pathakbau.musicwiki.adapter.ArtistTopListAdapter
import com.pathakbau.musicwiki.adapter.GenreListAdapter
import com.pathakbau.musicwiki.adapter.GenreTabListAdapter
import com.pathakbau.musicwiki.data.topGenres.Tag
import com.pathakbau.musicwiki.databinding.ArtistDetailsScreenBinding
import com.pathakbau.musicwiki.ui.MainActivity
import com.pathakbau.musicwiki.util.Resource
import com.pathakbau.musicwiki.viewmodel.MusicViewModel

private const val TAG = "ArtistDetailsFragment"

class ArtistDetailsFragment: Fragment() {

    private lateinit var binding: ArtistDetailsScreenBinding
    lateinit var viewModel: MusicViewModel
    private lateinit var tagsRVAdapter: GenreListAdapter
    private lateinit var topTracksRVAdapter: ArtistTopListAdapter
    private lateinit var topAlbumsRVAdapter: ArtistTopListAdapter

    val args: ArtistDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ArtistDetailsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        viewModel.requestArtistInfo(args.artistName)
        binding.artistName.text = args.artistName

        viewModel.artistInfo.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    //TODO: hide progress bar
                    response.data?.let { data ->
                        tagsRVAdapter.differ.submitList(data.artistInfoResponse.artist.tags.tag)
                        topTracksRVAdapter.differ.submitList(data.artistTopTracksResponse)
                        topAlbumsRVAdapter.differ.submitList(data.artistTopAlbumsResponse)
                        binding.playcount.text = data.artistInfoResponse.artist.stats.playcount
                        binding.followers.text = data.artistInfoResponse.artist.stats.listeners
                        binding.details.text = data.artistInfoResponse.artist.bio.summary
                    }
                }
                is Resource.Error -> {
                    //TODO: hide progress bar
                    response.message?.let { message ->
                        Toast.makeText(activity, "An Error Occurred!", Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "onViewCreated: $message")
                    }
                }
                is Resource.Loading -> {
                    //TODO: show progress bar
                }
            }
        }

        setupRV()
    }

    private fun setupRV() {
        tagsRVAdapter = GenreListAdapter()
        tagsRVAdapter.setOnItemClickListener { tag ->
            onGenreItemClick(tag)
        }
        binding.genres.apply {
            adapter = tagsRVAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        topTracksRVAdapter = ArtistTopListAdapter()
        binding.topTracks.apply {
            adapter = topTracksRVAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        topAlbumsRVAdapter = ArtistTopListAdapter()
        topAlbumsRVAdapter.setOnItemClickListener { item ->
            onAlbumItemClick(item.name, item.artistName)
        }
        binding.topAlbums.apply {
            adapter = topAlbumsRVAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun onGenreItemClick(tag: Tag) {
        val action = ArtistDetailsFragmentDirections
            .actionArtistDetailsFragmentToGenreDetailFragment(tag.name)
        findNavController().navigate(action)
    }

    private fun onAlbumItemClick(albumName: String, artistName: String) {
        //TODO: implement navigation
    }
}
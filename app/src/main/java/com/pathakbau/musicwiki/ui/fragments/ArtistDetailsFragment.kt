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
import com.bumptech.glide.Glide
import com.pathakbau.musicwiki.R
import com.pathakbau.musicwiki.adapter.ArtistTopListAdapter
import com.pathakbau.musicwiki.adapter.GenreListAdapter
import com.pathakbau.musicwiki.data.topGenres.Tag
import com.pathakbau.musicwiki.databinding.ArtistDetailsScreenBinding
import com.pathakbau.musicwiki.ui.MainActivity
import com.pathakbau.musicwiki.util.Resource
import com.pathakbau.musicwiki.util.formatCount
import com.pathakbau.musicwiki.viewmodel.MusicViewModel
import kotlinx.coroutines.processNextEventInCurrentThread

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
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.artistInfo.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { data ->
                        Glide.with(binding.thumbnail)
                            .load(data.artistInfoResponse.artist.image[3].text)
                            .placeholder(R.drawable.placeholder)
                            .into(binding.thumbnail)
                        tagsRVAdapter.differ.submitList(data.artistInfoResponse.artist.tags.tag)
                        topTracksRVAdapter.differ.submitList(data.artistTopTracksResponse)
                        topAlbumsRVAdapter.differ.submitList(data.artistTopAlbumsResponse)
                        binding.apply {
                            playcount.text = formatCount(
                                data.artistInfoResponse.artist.stats.playcount.toLong()
                            )
                            followers.text = formatCount(
                                data.artistInfoResponse.artist.stats.listeners.toLong()
                            )
                            details.text = data.artistInfoResponse.artist.bio.summary
                        }
                    }
                    binding.apply {
                        progressLayout.visibility = View.GONE
                        mainGroup1.visibility = View.VISIBLE
                        mainGroup2.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    binding.progressLayout.visibility = View.GONE
                    response.message?.let { message ->
                        Toast.makeText(activity, "An Error Occurred!", Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "onViewCreated: $message")
                    }
                }
                is Resource.Loading -> {
                    tagsRVAdapter.differ.submitList(ArrayList())
                    topTracksRVAdapter.differ.submitList(ArrayList())
                    topAlbumsRVAdapter.differ.submitList(ArrayList())
                    binding.apply {
                        mainGroup1.visibility = View.GONE
                        mainGroup2.visibility = View.INVISIBLE
                        progressLayout.visibility = View.VISIBLE
                    }
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
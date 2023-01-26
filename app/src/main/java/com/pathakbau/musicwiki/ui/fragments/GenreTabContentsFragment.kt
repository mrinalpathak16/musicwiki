package com.pathakbau.musicwiki.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pathakbau.musicwiki.adapter.GenreTabListAdapter
import com.pathakbau.musicwiki.data.genre.TabListItem
import com.pathakbau.musicwiki.databinding.TabContentsBinding
import com.pathakbau.musicwiki.ui.MainActivity
import com.pathakbau.musicwiki.util.Resource
import com.pathakbau.musicwiki.viewmodel.MusicViewModel

private const val ARG_TAB_TYPE = "type"
private const val TAG = "GenreTabContentsFragment"

class GenreTabContentsFragment : Fragment() {

    private var tabType: Int = 0

    private lateinit var binding: TabContentsBinding
    private lateinit var viewModel: MusicViewModel
    private lateinit var tabListAdapter: GenreTabListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tabType = it.getInt(ARG_TAB_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TabContentsBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        viewModel.genreInfo.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { data ->
                        viewModel.requestGenreTabContents(data.tag.name, tabType)
                    }
                }
                is Resource.Error -> {
                    //TODO: clear recycler view
                }
                is Resource.Loading -> {
                    //TODO: clear recycler view
                }
            }
        }

        setupRV()
    }

    private fun setupRV() {
        tabListAdapter = GenreTabListAdapter()
        tabListAdapter.setOnItemClickListener { item ->
            when (tabType) {
                0 -> onAlbumItemClick(item)
                1 -> onArtistItemClick(item)
                2 -> onTrackItemClick(item)
            }
        }
        binding.recyclerView.apply {
            adapter = tabListAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }

        val observationList = when (tabType) {
            0 -> viewModel.genreTabAlbums
            1 -> viewModel.genreTabArtists
            2 -> viewModel.genreTabTracks
            else -> throw RuntimeException("unexpected behaviour in tabs!!")
        }
        observationList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { data ->
                        tabListAdapter.differ.submitList(data)
                    }
                }
                is Resource.Error -> {
                    //TODO: clear recycler view
                    response.message?.let { message ->
                        Toast.makeText(activity, "An Error Occurred!", Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "onViewCreated: $message")
                    }
                }
                is Resource.Loading -> {
                    //TODO: clear recycler view
                }
            }
        }
    }

    private fun onAlbumItemClick(item: TabListItem) {
        val action = GenreDetailFragmentDirections
            .actionGenreDetailFragmentToAlbumDetailsFragment(item.text1, item.text2?:"")
        findNavController().navigate(action)
    }

    private fun onArtistItemClick(item: TabListItem) {
        val action = GenreDetailFragmentDirections
            .actionGenreDetailFragmentToArtistDetailsFragment(item.text1)
        findNavController().navigate(action)
    }

    private fun onTrackItemClick(item: TabListItem) {

    }
}
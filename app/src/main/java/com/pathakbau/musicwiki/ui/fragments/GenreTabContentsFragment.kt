package com.pathakbau.musicwiki.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pathakbau.musicwiki.adapter.GenreTabListAdapter
import com.pathakbau.musicwiki.data.genre.TabListItem
import com.pathakbau.musicwiki.databinding.TabContentsBinding
import com.pathakbau.musicwiki.ui.MainActivity
import com.pathakbau.musicwiki.util.Resource
import com.pathakbau.musicwiki.viewmodel.MusicViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val ARG_TAB_TYPE = "type"
const val ARG_TAG_NAME = "tagName"
private const val TAG = "GenreTabContentsFragment"

class GenreTabContentsFragment : Fragment() {

    private var tabType: Int = 0
    private lateinit var tagName: String

    private lateinit var binding: TabContentsBinding
    private lateinit var viewModel: MusicViewModel
    private lateinit var tabListAdapter: GenreTabListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tabType = it.getInt(ARG_TAB_TYPE)
            tagName = it.getString(ARG_TAG_NAME)?:""
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

        viewModel.requestGenreTabContents(tagName, tabType)

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
                    lifecycleScope.launch {
                        delay(500)
                        binding.apply {
                            progressLayout.visibility = View.GONE
                            mainGroup.visibility = View.VISIBLE
                        }
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
                    tabListAdapter.differ.submitList(ArrayList())
                    binding.apply {
                        mainGroup.visibility = View.INVISIBLE
                        progressLayout.visibility = View.VISIBLE
                    }
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
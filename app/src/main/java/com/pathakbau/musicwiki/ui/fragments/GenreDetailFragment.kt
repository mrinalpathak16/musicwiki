package com.pathakbau.musicwiki.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.pathakbau.musicwiki.adapter.GenreDetailTabsAdapter
import com.pathakbau.musicwiki.databinding.GenreDetailScreenBinding
import com.pathakbau.musicwiki.ui.MainActivity
import com.pathakbau.musicwiki.util.Resource
import com.pathakbau.musicwiki.viewmodel.MusicViewModel

class GenreDetailFragment: Fragment() {

    //TODO: Scroll behaviour buggy

    private val TAG = "GenreDetailFragment"

    private lateinit var binding: GenreDetailScreenBinding
    private lateinit var viewModel: MusicViewModel
    private lateinit var tabsAdapter: GenreDetailTabsAdapter
    val args : GenreDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GenreDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.collapsingLayout.title = args.tagName

        viewModel = (activity as MainActivity).viewModel
        viewModel.requestGenreInfo(args.tagName)

        tabsAdapter = GenreDetailTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab,position ->
            tab.text = when (position) {
                0 -> "Albums"
                1 -> "Artists"
                2 -> "Tracks"
                else -> ""
            }
        }.attach()

        viewModel.genreInfo.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    //TODO: hide progress bar
                    response.data?.let { data ->
                        binding.genreInfo.text = data.tag.wiki.summary
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
                    binding.genreInfo.text = ""
                    //TODO: show progress bar
                }
            }
        }
    }

}
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
import com.google.android.material.tabs.TabLayoutMediator
import com.pathakbau.musicwiki.adapter.GenreDetailTabsAdapter
import com.pathakbau.musicwiki.databinding.GenreDetailScreenBinding
import com.pathakbau.musicwiki.ui.MainActivity
import com.pathakbau.musicwiki.util.Resource
import com.pathakbau.musicwiki.viewmodel.MusicViewModel

private const val TAG = "GenreDetailFragment"

class GenreDetailFragment: Fragment() {

    //TODO: Scroll behaviour buggy

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
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel = (activity as MainActivity).viewModel
        viewModel.requestGenreInfo(args.tagName)

        tabsAdapter = GenreDetailTabsAdapter(this, args.tagName)
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
                    response.data?.let { data ->
                        binding.genreInfo.text = data.tag.wiki.summary
                    }
                    binding.apply {
                        progressLayout.visibility = View.GONE
                        mainGroup.visibility = View.VISIBLE
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
                    binding.apply {
                        mainGroup.visibility = View.GONE
                        progressLayout.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

}
package com.pathakbau.musicwiki.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pathakbau.musicwiki.R
import com.pathakbau.musicwiki.adapter.GenreListAdapter
import com.pathakbau.musicwiki.data.topGenres.Tag
import com.pathakbau.musicwiki.databinding.FirstScreenBinding
import com.pathakbau.musicwiki.viewmodel.FirstScreenModes
import com.pathakbau.musicwiki.ui.MainActivity
import com.pathakbau.musicwiki.viewmodel.MusicViewModel
import com.pathakbau.musicwiki.util.Resource

class FirstScreenFragment: Fragment() {

    private val TAG = "FirstScreenFragment"

    private lateinit var binding: FirstScreenBinding
    private lateinit var topGenresAdapter: GenreListAdapter
    private lateinit var viewModel: MusicViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FirstScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setupRV()

        binding.chooseGenreTextView.setOnClickListener {
            viewModel.firstScreenMode.apply {
                if (value == FirstScreenModes.COLLAPSED) postValue(FirstScreenModes.EXPANDED)
                else postValue(FirstScreenModes.COLLAPSED)
            }
        }

        viewModel.topGenres.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    //TODO: hide progress bar
                    response.data?.let { data ->
                        topGenresAdapter.differ.submitList(data.topTags.tags)
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

        viewModel.firstScreenMode.observe(viewLifecycleOwner) { mode ->
            when (mode) {
                FirstScreenModes.COLLAPSED -> binding.chooseGenreTextView
                    .setCompoundDrawablesRelativeWithIntrinsicBounds(
                        null,
                        null,
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_expand_circle_down_24px,
                            null
                        ),
                        null
                    )
                FirstScreenModes.EXPANDED -> binding.chooseGenreTextView
                    .setCompoundDrawablesRelativeWithIntrinsicBounds(
                        null,
                        null,
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_collapse_circle_up_24px,
                            null
                        ),
                        null
                    )
            }
            topGenresAdapter.listMode = mode
            topGenresAdapter.notifyDataSetChanged()
        }
    }

    private fun setupRV() {
        topGenresAdapter = GenreListAdapter()
        topGenresAdapter.setOnItemClickListener { tag ->
            onGenreItemClick(tag)
        }
        binding.genres.apply {
            adapter = topGenresAdapter
            layoutManager = GridLayoutManager(activity,3)
        }

    }

    private fun onGenreItemClick(tag: Tag) {
        val action = FirstScreenFragmentDirections
            .actionFirstScreenFragmentToGenreDetailFragment(tag.name)
        findNavController().navigate(action)
    }
}
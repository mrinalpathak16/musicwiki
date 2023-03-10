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
import com.pathakbau.musicwiki.adapter.GenreListAdapter
import com.pathakbau.musicwiki.data.topGenres.Tag
import com.pathakbau.musicwiki.databinding.AlbumDetailsScreenBinding
import com.pathakbau.musicwiki.ui.MainActivity
import com.pathakbau.musicwiki.util.Resource
import com.pathakbau.musicwiki.viewmodel.MusicViewModel

private const val TAG = "AlbumDetailsFragment"

class AlbumDetailsFragment: Fragment() {

    private lateinit var binding: AlbumDetailsScreenBinding
    lateinit var viewModel: MusicViewModel
    private lateinit var rvAdapter: GenreListAdapter

    val args : AlbumDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AlbumDetailsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        viewModel.requestAlbumInfo(args.albumName, args.artistName)
        binding.labelPrimary.text = args.albumName
        binding.labelSecondary.text = args.artistName
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.albumInfo.observe(viewLifecycleOwner) {response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { data ->
                        Glide.with(binding.thumbnail)
                            .load(data.album.image[3].text)
                            .placeholder(R.drawable.placeholder)
                            .into(binding.thumbnail)
                        binding.details.text = data.album.wiki?.summary
                        rvAdapter.differ.submitList(data.album.tags.tag)
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
                        mainGroup.visibility = View.INVISIBLE
                        progressLayout.visibility = View.VISIBLE
                    }
                }
            }
        }

        setupRV()
    }

    private fun setupRV() {
        rvAdapter = GenreListAdapter()
        rvAdapter.setOnItemClickListener { tag ->
            onGenreItemClick(tag)
        }
        binding.genres.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun onGenreItemClick(tag: Tag) {
        val action = AlbumDetailsFragmentDirections
            .actionAlbumDetailsFragmentToGenreDetailFragment(tag.name)
        findNavController().navigate(action)
    }


}
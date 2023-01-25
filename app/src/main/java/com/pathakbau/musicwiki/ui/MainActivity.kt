package com.pathakbau.musicwiki.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pathakbau.musicwiki.databinding.ActivityMainBinding
import com.pathakbau.musicwiki.viewmodel.MusicViewModel

class MainActivity : AppCompatActivity() {

    val viewModel: MusicViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
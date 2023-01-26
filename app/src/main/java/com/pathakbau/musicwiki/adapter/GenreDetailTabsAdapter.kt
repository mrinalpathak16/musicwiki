package com.pathakbau.musicwiki.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pathakbau.musicwiki.ui.fragments.GenreTabContentsFragment

class GenreDetailTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = GenreTabContentsFragment()
        fragment.arguments = Bundle().apply {
            putInt("type", position)
        }
        return fragment
    }
}
package com.pathakbau.musicwiki.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pathakbau.musicwiki.ui.fragments.ARG_TAB_TYPE
import com.pathakbau.musicwiki.ui.fragments.ARG_TAG_NAME
import com.pathakbau.musicwiki.ui.fragments.GenreTabContentsFragment

class GenreDetailTabsAdapter(fragment: Fragment, val tagName: String) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = GenreTabContentsFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_TAB_TYPE, position)
            putString(ARG_TAG_NAME, tagName)
        }
        return fragment
    }
}
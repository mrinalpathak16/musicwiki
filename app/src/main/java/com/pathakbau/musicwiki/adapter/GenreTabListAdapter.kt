package com.pathakbau.musicwiki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pathakbau.musicwiki.data.genre.TabListItem
import com.pathakbau.musicwiki.databinding.TabContentItemBinding

class GenreTabListAdapter() :
    RecyclerView.Adapter<GenreTabListAdapter.TabListViewHolder>() {

    val differ = AsyncListDiffer<TabListItem>(this, object : DiffUtil.ItemCallback<TabListItem>() {
        override fun areItemsTheSame(oldItem: TabListItem, newItem: TabListItem): Boolean {
            return oldItem.text1 == newItem.text2
        }

        override fun areContentsTheSame(oldItem: TabListItem, newItem: TabListItem): Boolean {
            return oldItem == newItem
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            GenreTabListAdapter.TabListViewHolder {
        return TabListViewHolder(
            TabContentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
                .root
        )
    }

    override fun onBindViewHolder(holder: TabListViewHolder, position: Int) {
        val tabListItem = differ.currentList[position]
        holder.binding.apply {
            textPrimary.text = tabListItem.text1
            textSecondary.text = tabListItem.text2
            root.setOnClickListener {
                this@GenreTabListAdapter.onItemClickListener?.let { it(tabListItem) }
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class TabListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: TabContentItemBinding
        init {
            binding = TabContentItemBinding.bind(itemView)
        }
    }

    private var onItemClickListener: ((TabListItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (TabListItem) -> Unit) {
        onItemClickListener = listener
    }
}
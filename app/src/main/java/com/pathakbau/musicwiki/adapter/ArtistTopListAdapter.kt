package com.pathakbau.musicwiki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pathakbau.musicwiki.data.artist.TopListItem
import com.pathakbau.musicwiki.databinding.TopItemBinding

class ArtistTopListAdapter : RecyclerView.Adapter<ArtistTopListAdapter.TopItemViewHolder>() {

    val differ = AsyncListDiffer<TopListItem>(this, object : DiffUtil.ItemCallback<TopListItem>() {
        override fun areItemsTheSame(oldItem: TopListItem, newItem: TopListItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: TopListItem, newItem: TopListItem): Boolean {
            return oldItem == newItem
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ArtistTopListAdapter.TopItemViewHolder {
        return TopItemViewHolder(
            TopItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
                .root
        )
    }

    override fun onBindViewHolder(holder: TopItemViewHolder, position: Int) {
        val tabListItem = differ.currentList[position]
        holder.binding.apply {
            textPrimary.text = tabListItem.name
            textSecondary.text = tabListItem.artistName
            root.setOnClickListener {
                this@ArtistTopListAdapter.onItemClickListener?.let { it(tabListItem) }
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class TopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: TopItemBinding
        init {
            binding = TopItemBinding.bind(itemView)
        }
    }

    private var onItemClickListener: ((TopListItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (TopListItem) -> Unit) {
        onItemClickListener = listener
    }
}
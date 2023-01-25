package com.pathakbau.musicwiki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pathakbau.musicwiki.data.topGenres.Tag
import com.pathakbau.musicwiki.databinding.GenreItemBinding
import com.pathakbau.musicwiki.viewmodel.FirstScreenModes
import kotlin.math.min

class GenreListAdapter : RecyclerView.Adapter<GenreListAdapter.GenreViewHolder>() {

    var listMode: FirstScreenModes = FirstScreenModes.COLLAPSED

    val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem == newItem
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            GenreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
                .root
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val tag = differ.currentList[position]
        holder.binding.apply {
            genreName.text = tag?.name
            root.setOnClickListener {
                this@GenreListAdapter.onItemClickListener?.let { it(tag) }
            }
        }
    }

    override fun getItemCount(): Int =
        when (listMode) {
            FirstScreenModes.COLLAPSED -> min(differ.currentList.size, 10)
            FirstScreenModes.EXPANDED -> differ.currentList.size
        }


    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: GenreItemBinding
        init {
            binding =GenreItemBinding.bind(itemView)
        }
    }

    private var onItemClickListener: ((Tag) -> Unit)? = null

    fun setOnItemClickListener(listener: (Tag) -> Unit) {
        onItemClickListener = listener
    }
}
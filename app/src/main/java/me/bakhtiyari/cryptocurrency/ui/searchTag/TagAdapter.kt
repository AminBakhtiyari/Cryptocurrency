package me.bakhtiyari.cryptocurrency.ui.searchTag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.bakhtiyari.cryptocurrency.databinding.ItemTagBinding
import me.bakhtiyari.cryptocurrency.presentation.model.TagUIModel
import javax.inject.Inject

class TagAdapter @Inject constructor() :
    PagingDataAdapter<TagUIModel, TagAdapter.TagViewHolder>(TagComparator) {
    var tagClickListener: TagClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TagViewHolder(
            ItemTagBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class TagViewHolder(private val binding: ItemTagBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                tagClickListener?.onTagClicked(
                    binding,
                    getItem(absoluteAdapterPosition) as TagUIModel
                )
            }
        }

        fun bind(model: TagUIModel) = with(binding) {
            item = model
        }
    }

    object TagComparator : DiffUtil.ItemCallback<TagUIModel>() {
        override fun areItemsTheSame(oldItem: TagUIModel, newItem: TagUIModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TagUIModel, newItem: TagUIModel) =
            oldItem == newItem
    }

    interface TagClickListener {
        fun onTagClicked(binding: ItemTagBinding, tag: TagUIModel)
    }
}
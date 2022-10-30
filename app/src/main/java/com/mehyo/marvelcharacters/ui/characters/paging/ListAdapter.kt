package com.mehyo.marvelcharacters.ui.characters.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.mehyo.marvelcharacters.R
import com.mehyo.marvelcharacters.data.Character
import com.mehyo.marvelcharacters.databinding.ItemRowBinding

class ListAdapter(
    private val onCharacterCardClickListener: (Character) -> Unit = {}
) : PagingDataAdapter<Character, ListAdapter.ListViewHolder>(ITEM_COMPARATOR) {

    /**
     * ViewHolder created for the Recyclerview Adapter to
     * set the data in each row in the UI and to
     * implement on item ClickListener.
     */
    inner class ListViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Character, onCharacterCardClickListener: (Character) -> Unit) {
            val imgUrl =
                "${data.thumbnail?.path}.${data.thumbnail?.extension}".replace("http:", "https:")
            binding.apply {
                tvName.text = data.name
                ivAvatar.load(imgUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_image)
                    transformations(CircleCropTransformation())
                }
                cardview.setOnClickListener {
                    onCharacterCardClickListener(data)
                }
            }
        }
    }

    /**
     * checking and comparing data changes.
     */
    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character) =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val characterItem = getItem(position)
        characterItem?.let { character ->
            holder.bind(character, onCharacterCardClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
}
package com.mehyo.marvelcharacters.ui.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mehyo.marvelcharacters.R
import com.mehyo.marvelcharacters.data.DefaultObject
import com.mehyo.marvelcharacters.databinding.SmallItemRowBinding

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {

    private var list = arrayListOf<DefaultObject>()

    inner class DetailsViewHolder(private val binding: SmallItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        /**
         * Set the data in each row in the UI
         */
        fun bind(book: DefaultObject) {
            binding.apply {
                tvBookName.text = book.title
                val imageUrl =
                    "${book.thumbnail?.path}.${book.thumbnail?.extension}"
                        .replace("http:", "https:")
                ivBook.load(imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_image)
                    error(R.drawable.ic_image)
                }
            }
        }
    }

    /**
     * function for adding
     * all the posts to the list
     * then updating it.
     */
    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<DefaultObject>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DetailsViewHolder(
        SmallItemRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size
}
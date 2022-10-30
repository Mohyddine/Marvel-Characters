package com.mehyo.marvelcharacters.ui.characters.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.mehyo.marvelcharacters.R
import com.mehyo.marvelcharacters.databinding.ItemLoadStateBinding

class LoadStateViewHolder(
    private val binding: ItemLoadStateBinding,
    private val retry: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRefresh.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.tvError.text = loadState.error.localizedMessage
        }
        binding.apply {
            tvError.isVisible = loadState !is LoadState.Loading
            progressBar.isVisible =
                if (loadState !is LoadState.Loading) loadState.endOfPaginationReached else !loadState.endOfPaginationReached
            btnRefresh.isVisible = loadState !is LoadState.Loading
        }
    }

    companion object {
        fun create(
            parent: ViewGroup, retry: () -> Unit
        ): LoadStateViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_load_state, parent, false)
            val binding = ItemLoadStateBinding.bind(view)
            return LoadStateViewHolder(binding, retry)
        }
    }
}
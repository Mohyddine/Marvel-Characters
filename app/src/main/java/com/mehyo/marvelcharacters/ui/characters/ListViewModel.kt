package com.mehyo.marvelcharacters.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mehyo.marvelcharacters.repository.NetworkRepository
import com.mehyo.marvelcharacters.ui.characters.paging.MarvelPagingSource

class ListViewModel(
    private val repository: NetworkRepository
) : ViewModel() {

    val data = Pager(PagingConfig(1)) {
        MarvelPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}
package com.mehyo.marvelcharacters.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.mehyo.marvelcharacters.data.Character
import com.mehyo.marvelcharacters.databinding.FragmentListBinding
import com.mehyo.marvelcharacters.ui.characters.paging.ListAdapter
import com.mehyo.marvelcharacters.ui.characters.paging.ListLoadStateAdapter
import com.mehyo.marvelcharacters.utils.gone
import com.mehyo.marvelcharacters.utils.visible
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by sharedViewModel()
    private val listAdapter by lazy {
        ListAdapter(
            onCharacterCardClickListener = { cardData -> onCardChecked(cardData) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObservables()
        initListeners()
    }

    private fun initListeners() {
        binding.btnRefresh.setOnClickListener {
            listAdapter.retry()
        }
        listAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    binding.listFragment.gone()
                    binding.shimmer.visible()
                    binding.shimmer.startShimmer()
                    binding.tvError.gone()
                    binding.btnRefresh.gone()
                    binding.listFragment.gone()
                }
                is LoadState.NotLoading -> {
                    binding.shimmer.gone()
                    binding.shimmer.stopShimmer()
                    binding.tvError.gone()
                    binding.btnRefresh.gone()
                    binding.listFragment.visible()
                }
                is LoadState.Error -> {
                    binding.shimmer.gone()
                    binding.shimmer.stopShimmer()
                    binding.tvError.visible()
                    binding.btnRefresh.visible()
                    binding.listFragment.gone()
                }
            }
            handleError(loadState)
        }
        binding.rvCharacters.adapter = listAdapter.withLoadStateHeaderAndFooter(
            header = ListLoadStateAdapter { listAdapter.retry() },
            footer = ListLoadStateAdapter { listAdapter.retry() }
        )
    }

    private fun handleError(loadStates: CombinedLoadStates) {
        val errorState = loadStates.append as? LoadState.Error
            ?: loadStates.prepend as? LoadState.Error
        errorState?.let {
            Toast.makeText(requireContext(), it.error.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun initViews() {
        binding.apply {
            rvCharacters.apply {
                adapter = listAdapter
            }
        }
    }

    private fun initObservables() {
        lifecycleScope.launch {
            viewModel.data.collect { pagingData ->
                listAdapter.submitData(pagingData)
            }
        }
    }

    private fun onCardChecked(character: Character) {
        findNavController().navigate(
            ListFragmentDirections.actionListFragmentToDetailsFragment(
                character.id
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.mehyo.marvelcharacters.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mehyo.marvelcharacters.databinding.FragmentListBinding
import com.mehyo.marvelcharacters.ui.characters.paging.ListAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by sharedViewModel()
    private val listAdapter by lazy { ListAdapter() }

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
        binding.apply {
            fabSearch.setOnClickListener {
                findNavController().navigate(
                    ListFragmentDirections.actionListFragmentToDetailsFragment(
                        1017100
                    )
                )
            }
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
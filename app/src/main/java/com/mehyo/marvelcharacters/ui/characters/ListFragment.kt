package com.mehyo.marvelcharacters.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mehyo.marvelcharacters.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservables()
        initViews()
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            fabSearch.setOnClickListener {
                findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailsFragment())
            }
        }
    }

    private fun initViews() {
    }

    private fun initObservables() {
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
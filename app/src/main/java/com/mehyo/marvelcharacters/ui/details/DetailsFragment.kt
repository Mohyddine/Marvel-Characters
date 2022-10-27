package com.mehyo.marvelcharacters.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mehyo.marvelcharacters.databinding.FragmentDetailsBinding
import com.mehyo.marvelcharacters.network.ResourceState
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val detailsViewModel: DetailsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("mehyos", "start")
        detailsViewModel.getCharacterByIdAsync(1011334)
        initObservables()
        initViews()
        initListeners()
    }

    private fun initListeners() {
    }

    private fun initViews() {
    }

    private fun initObservables() {
        detailsViewModel.characterDetailsResultLiveData.observe(viewLifecycleOwner) { result ->
            when (result.state) {
                is ResourceState.LOADING -> {
                    //binding.apply {
                    //    progressBar.visible()
                    //    fabAdd.gone()
                    //    tvError.gone()
                    //    btnRefresh.gone()
                    //}
                    Log.d("mehyos", "loading")
                }

                is ResourceState.SUCCESS -> {
                    result.data?.let { character ->
                        //binding.apply {
                        //    fabAdd.visible()
                        //    progressBar.gone()
                        //    tvError.gone()
                        //    btnRefresh.gone()
                        //}
                        //initList(listPostsData)
                        Log.d("mehyos", "success")
                        Log.d("mehyos", "$character")
                    }
                }

                is ResourceState.ERROR -> {
                    //binding.apply {
                    //    progressBar.gone()
                    //    fabAdd.gone()
                    //    tvError.visible()
                    //    btnRefresh.visible()
                    //}
                    //initList(emptyList())
                    Log.d("mehyos", "error")
                    Log.d("mehyos", result.exception?.localizedMessage.toString())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
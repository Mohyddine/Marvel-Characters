package com.mehyo.marvelcharacters.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.mehyo.marvelcharacters.R
import com.mehyo.marvelcharacters.data.Character
import com.mehyo.marvelcharacters.databinding.FragmentDetailsBinding
import com.mehyo.marvelcharacters.network.ResourceState
import com.mehyo.marvelcharacters.utils.gone
import com.mehyo.marvelcharacters.utils.visible
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val detailsViewModel: DetailsViewModel by sharedViewModel()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.characterId == -1) {
            requireActivity().onBackPressed()
            Toast.makeText(requireContext(), getText(R.string.general_error), Toast.LENGTH_SHORT)
                .show()
        }
        getData(args.characterId)
        initObservables()
        initViews()
        initListeners()
    }

    private fun getData(CharacterId: Int) {
        detailsViewModel.getCharacterByIdAsync(CharacterId)
        //detailsViewModel.getCharacterComicsByIdAsync(CharacterId)
        //detailsViewModel.getCharacterEventsByIdAsync(CharacterId)
        //detailsViewModel.getCharacterStoriesByIdAsync(CharacterId)
        //detailsViewModel.getCharacterSeriesByIdAsync(CharacterId)
    }

    private fun initListeners() {
        binding.btnRefresh.setOnClickListener {
            getData(args.characterId)
        }
    }

    private fun initViews() {
    }

    private fun initCharacterInfo(character: Character) {
        val imageUrl =
            "${character.thumbnail?.path}.${character.thumbnail?.extension}"
                .replace("http:", "https:")
        binding.apply {
            ivCharacter.load(imageUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_image)
                transformations(CircleCropTransformation())
                error(R.drawable.ic_image)
            }
            tvId.text = character.id.toString()
            tvName.text = character.name
            tvBio.text =
                if (character.description.isNullOrBlank()) "bio is not available" else character.description
        }
    }

    private fun initObservables() {
        detailsViewModel.characterDetailsResultLiveData.observe(viewLifecycleOwner) { result ->
            when (result.state) {
                is ResourceState.LOADING -> {
                    binding.apply {
                        detailsFragment.gone()
                        shimmer.visible()
                        shimmer.startShimmer()
                        tvError.gone()
                        btnRefresh.gone()
                    }
                }

                is ResourceState.SUCCESS -> {
                    result.data?.let { character ->
                        binding.apply {
                            shimmer.gone()
                            shimmer.stopShimmer()
                            detailsFragment.visible()
                        }
                        initCharacterInfo(character)
                    }
                }

                is ResourceState.ERROR -> {
                    binding.apply {
                        shimmer.gone()
                        detailsFragment.gone()
                        shimmer.stopShimmer()
                        tvError.visible()
                        btnRefresh.visible()
                    }
                }
            }
        }

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
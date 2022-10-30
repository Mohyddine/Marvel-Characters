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
import com.mehyo.marvelcharacters.data.DefaultObject
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
    private val comicsAdapter by lazy { DetailsAdapter() }
    private val eventsAdapter by lazy { DetailsAdapter() }
    private val storiesAdapter by lazy { DetailsAdapter() }
    private val seriesAdapter by lazy { DetailsAdapter() }

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
        detailsViewModel.getCharacterComicsByIdAsync(CharacterId)
        detailsViewModel.getCharacterEventsByIdAsync(CharacterId)
        detailsViewModel.getCharacterStoriesByIdAsync(CharacterId)
        detailsViewModel.getCharacterSeriesByIdAsync(CharacterId)
    }

    private fun initListeners() {
        binding.btnRefresh.setOnClickListener {
            getData(args.characterId)
        }
        binding.ivRefresh.setOnClickListener {
            getData(args.characterId)
        }
    }

    private fun initViews() {
        binding.rvComics.adapter = comicsAdapter
        binding.rvEvents.adapter = eventsAdapter
        binding.rvStories.adapter = storiesAdapter
        binding.rvSeries.adapter = seriesAdapter
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

        detailsViewModel.comicsResultLiveData.observe(viewLifecycleOwner) { result ->
            when (result.state) {
                is ResourceState.LOADING -> {
                    Log.d("mehyos", "loading comics")
                    binding.tvComics.gone()
                    binding.rvComics.gone()
                }

                is ResourceState.SUCCESS -> {
                    result.data?.let { comicsList: List<DefaultObject> ->
                        binding.tvComics.visible()
                        binding.rvComics.visible()
                        Log.d("mehyos", "success comics")
                        if (comicsList.isEmpty()) {
                            binding.tvComics.gone()
                            binding.rvComics.gone()
                        }
                        comicsAdapter.setData(comicsList)
                    }
                }

                is ResourceState.ERROR -> {
                    binding.tvComics.gone()
                    binding.rvComics.gone()
                    comicsAdapter.setData(emptyList())
                    Log.d("mehyos", "error comics")
                    Log.d("mehyos", result.exception?.localizedMessage.toString())
                }
            }
        }

        detailsViewModel.eventsResultLiveData.observe(viewLifecycleOwner) { result ->
            when (result.state) {
                is ResourceState.LOADING -> {
                    Log.d("mehyos", "loading events")
                    binding.tvEvents.gone()
                    binding.rvEvents.gone()
                }

                is ResourceState.SUCCESS -> {
                    result.data?.let { eventsList: List<DefaultObject> ->
                        binding.tvEvents.visible()
                        binding.rvEvents.visible()
                        Log.d("mehyos", "success events")
                        if (eventsList.isEmpty()) {
                            binding.tvEvents.gone()
                            binding.rvEvents.gone()
                        }
                        eventsAdapter.setData(eventsList)
                    }
                }

                is ResourceState.ERROR -> {
                    binding.tvEvents.gone()
                    binding.rvEvents.gone()
                    comicsAdapter.setData(emptyList())
                    Log.d("mehyos", "error events")
                    Log.d("mehyos", result.exception?.localizedMessage.toString())
                }
            }
        }

        detailsViewModel.storiesResultLiveData.observe(viewLifecycleOwner) { result ->
            when (result.state) {
                is ResourceState.LOADING -> {
                    Log.d("mehyos", "loading stories")
                    binding.tvStories.gone()
                    binding.rvStories.gone()
                }

                is ResourceState.SUCCESS -> {
                    result.data?.let { storiesList: List<DefaultObject> ->
                        binding.tvStories.visible()
                        binding.rvStories.visible()
                        Log.d("mehyos", "success stories")
                        if (storiesList.isEmpty()) {
                            binding.tvStories.gone()
                            binding.rvStories.gone()
                        }
                        storiesAdapter.setData(storiesList)
                    }
                }

                is ResourceState.ERROR -> {
                    binding.tvStories.gone()
                    binding.rvStories.gone()
                    storiesAdapter.setData(emptyList())
                    Log.d("mehyos", "error stories")
                    Log.d("mehyos", result.exception?.localizedMessage.toString())
                }
            }
        }

        detailsViewModel.seriesResultLiveData.observe(viewLifecycleOwner) { result ->
            when (result.state) {
                is ResourceState.LOADING -> {
                    Log.d("mehyos", "loading series")
                    binding.tvSeries.gone()
                    binding.rvSeries.gone()
                }

                is ResourceState.SUCCESS -> {
                    result.data?.let { seriesList: List<DefaultObject> ->
                        binding.tvSeries.visible()
                        binding.rvSeries.visible()
                        Log.d("mehyos", "success series")
                        if (seriesList.isEmpty()) {
                            binding.tvSeries.gone()
                            binding.rvSeries.gone()
                        }
                        seriesAdapter.setData(seriesList)
                    }
                }

                is ResourceState.ERROR -> {
                    binding.tvSeries.gone()
                    binding.rvSeries.gone()
                    seriesAdapter.setData(emptyList())
                    Log.d("mehyos", "error series")
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
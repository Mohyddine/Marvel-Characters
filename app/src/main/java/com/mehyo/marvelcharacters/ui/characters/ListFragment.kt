package com.mehyo.marvelcharacters.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import com.mehyo.marvelcharacters.databinding.FragmentListBinding
import com.mehyo.marvelcharacters.utils.gone
import com.mehyo.marvelcharacters.utils.visible

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
                searchView.apply {
                    visible()
                    setIconifiedByDefault(true)
                    isFocusable = true
                    isIconified = false
                }
            }
            searchView.setOnCloseListener {
                searchView.apply {
                    gone()
                    clearFocus()
                }
                false
            }
            searchView.setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchView.apply {
                        gone()
                        clearFocus()
                        setQuery("", false)
                    }
                    return false
                }
            })
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
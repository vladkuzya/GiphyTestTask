package com.example.giphytesttask.screens.gif_search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.giphytesttask.R
import com.example.giphytesttask.base.BaseFragment
import com.example.giphytesttask.databinding.FragmentGifSearchBinding
import com.example.giphytesttask.model.GetGifsResponse
import com.example.giphytesttask.model.Resource
import com.example.giphytesttask.util.view_model.ViewModelFactory
import javax.inject.Inject

class GifSearchFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: GifSearchViewModel
    private var binding: FragmentGifSearchBinding? = null

    override fun getContentView(): Int = R.layout.fragment_gif_search

    override fun setupDataBinding(dataBinding: ViewDataBinding?) {
        binding = dataBinding as FragmentGifSearchBinding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[GifSearchViewModel::class.java]
        initSearchEditText()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.gifsLiveData.observe(viewLifecycleOwner) {
            handleRecipesList(it)
        }
    }

    private fun handleRecipesList(status: Resource<GetGifsResponse>) {
//        when (status) {
//            is Resource.Loading -> showLoadingView()
//            is Resource.Success -> status.data?.let { bindListData(recipes = it) }
//            is Resource.DataError -> {
//                showDataView(false)
//                status.errorCode?.let { recipesListViewModel.showToastMessage(it) }
//            }
//        }
        Toast.makeText(requireContext(), status.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun initSearchEditText() {
        binding?.etSearch?.addTextChangedListener { editable ->
            viewModel.search(editable.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
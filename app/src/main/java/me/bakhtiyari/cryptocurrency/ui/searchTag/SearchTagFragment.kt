package me.bakhtiyari.cryptocurrency.ui.searchTag

import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.bakhtiyari.cryptocurrency.R
import me.bakhtiyari.cryptocurrency.base.BaseFragment
import me.bakhtiyari.cryptocurrency.databinding.FragmentSearchTagBinding
import me.bakhtiyari.cryptocurrency.databinding.ItemTagBinding
import me.bakhtiyari.cryptocurrency.domain.model.ErrorEntity
import me.bakhtiyari.cryptocurrency.domain.model.ErrorModel
import me.bakhtiyari.cryptocurrency.presentation.model.TagUIModel
import me.bakhtiyari.cryptocurrency.presentation.viewmodel.SearchTagsViewModel
import javax.inject.Inject

@AndroidEntryPoint
class SearchTagFragment : BaseFragment<FragmentSearchTagBinding, SearchTagsViewModel>(R.layout.fragment_search_tag),
    TagAdapter.TagClickListener, SearchView.OnQueryTextListener {

    override val viewModel: SearchTagsViewModel by viewModels()

    @Inject
    lateinit var tagAdapter: TagAdapter


    private val searchQueryObserver = Observer<String> {
        viewModel.search()
    }

    private val searchResultObserver = Observer<PagingData<TagUIModel>> {
        lifecycleScope.launch {
            tagAdapter.submitData(it)
        }

    }

    private val onErrorObserver = Observer<String> {
        showError(it)
    }

    private val onSelectedTagObserver = Observer<TagUIModel> {
        //TODO:
        Log.i("onSelectedTag", it.name ?: "")
    }

    override fun initVariables() {
        binding.viewModel = viewModel
        binding.adapter = tagAdapter
    }

    override fun initObserves() {
        viewModel.searchQuery.observe(this, searchQueryObserver)
        viewModel.searchResult.observe(this, searchResultObserver)
        viewModel.errorMessage.observe(this, onErrorObserver)
        viewModel.selectedTagEvent.observe(this, onSelectedTagObserver)
    }

    override fun initViews() {
        viewModel.search()
    }

    override fun viewsCreated(binding: FragmentSearchTagBinding, vm: SearchTagsViewModel) {
        with(binding) {
            searchBox.setOnQueryTextListener(this@SearchTagFragment)
            with(tagAdapter) {
                tagClickListener = this@SearchTagFragment
                addLoadStateListener {
                    updateUIWithPagingState(it)
                }
            }
        }

    }

    override fun onTagClicked(binding: ItemTagBinding, tag: TagUIModel) {
        viewModel.onSelectedTag(tag)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.searchQuery.value = query
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.searchQuery.value = newText
        return false
    }

    private fun updateUIWithPagingState(loadState: CombinedLoadStates) {
        // show empty list
        val isListEmpty =
            loadState.refresh is LoadState.NotLoading && tagAdapter.itemCount == 0
        viewModel.isEmptyList.postValue(isListEmpty)

        // Only show the list if refresh succeeds, either from the the local db or the remote.
        val isShowList =
            loadState.source.refresh is LoadState.NotLoading || loadState.mediator?.refresh is LoadState.NotLoading
        viewModel.isShowList.postValue(isShowList)

        // Show loading spinner during initial load or refresh.
        val isLoading = loadState.mediator?.refresh is LoadState.Loading
        viewModel.isLoading.postValue(isLoading)

        val errorState = when {
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            else -> null
        }
        errorState?.let {
            viewModel.handleError(
                ErrorModel(
                    it.error,
                    ErrorEntity.Paging,
                    it.error.message
                )
            )
        }
    }

}
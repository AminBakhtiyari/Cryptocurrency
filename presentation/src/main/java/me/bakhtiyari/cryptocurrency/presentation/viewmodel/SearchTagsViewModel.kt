package me.bakhtiyari.cryptocurrency.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import me.bakhtiyari.cryptocurrency.domain.model.TagModel
import me.bakhtiyari.cryptocurrency.domain.usecase.GetTagsUseCase
import me.bakhtiyari.cryptocurrency.presentation.mapper.TagUIModelMapper
import me.bakhtiyari.cryptocurrency.presentation.model.TagUIModel
import javax.inject.Inject

@HiltViewModel
class SearchTagsViewModel @Inject constructor(
    private val getTagsUseCase: GetTagsUseCase,
    private val mapper: TagUIModelMapper
) : BaseViewModel() {

    private val _selectedTagEvent = MutableLiveData<TagUIModel>()
    val selectedTagEvent: LiveData<TagUIModel> = _selectedTagEvent

    val searchQuery = MutableLiveData("")
    val searchResult = MutableLiveData<PagingData<TagUIModel>>()
    val isEmptyList = MutableLiveData(false)
    val isShowList = MutableLiveData(false)

    init {
        getTags()
    }


    private fun getTags() {
        getTagsUseCase.execute { result ->
            result.handleResult(successBlock = ::onGetTagsResult, failureBlock = ::handleError)
        }
    }

    private fun onGetTagsResult(data: PagingData<TagModel>) {

        this.searchResult.postValue(data.map {
            mapper.mapTo(it)
        })
        this.searchResult.cachedIn(viewModelScope)
    }

    fun search() {}

    fun onSelectedTag(tag: TagUIModel) {
        _selectedTagEvent.value = tag
    }
}
package me.bakhtiyari.cryptocurrency.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.bakhtiyari.cryptocurrency.domain.model.ErrorModel
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData(false)

    fun handleError(error: ErrorModel) {
        Timber.e(error.throwable)
        this.errorMessage.postValue(error.originalMessage)

    }

}
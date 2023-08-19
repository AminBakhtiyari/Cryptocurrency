package me.bakhtiyari.cryptocurrency.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.bakhtiyari.cryptocurrency.presentation.utils.SingleEvent
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel() {

    private val _onStartMainEvent = MutableLiveData<SingleEvent<Boolean>>()
    val onStartMainEvent: LiveData<SingleEvent<Boolean>> = _onStartMainEvent

    fun initSplashScreen() {
        viewModelScope.launch {
            delay(2000)
            _onStartMainEvent.postValue(SingleEvent(true))
        }
    }
}
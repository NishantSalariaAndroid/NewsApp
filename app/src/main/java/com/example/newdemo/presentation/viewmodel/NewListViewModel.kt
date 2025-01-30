package com.example.newdemo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newdemo.domain.usecases.FetchNewsUseCase
import kotlinx.coroutines.launch


class NewListViewModel(
    private val fetchNewsUseCase: FetchNewsUseCase,
) : ViewModel() {
    private val _newsArticles = MutableLiveData<List<Any>>()
    val newsArticles: LiveData<List<Any>> get() = _newsArticles
    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar: LiveData<Boolean> get() = _showProgressBar

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        fetchNews()
    }

    // Get the news articles from the use case and update the live data
    private fun fetchNews() {
        _showProgressBar.value = true
        viewModelScope.launch {
            try {
                val result = fetchNewsUseCase.execute()
                result.onSuccess {
                    _newsArticles.value = it
                    _showProgressBar.value = false
                }.onFailure {
                    _errorMessage.value = it.message
                    _showProgressBar.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _showProgressBar.value = false
            }

        }
    }


}

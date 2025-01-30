package com.example.newdemo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newdemo.data.models.Article
import com.example.newdemo.domain.usecases.GetFavoriteArticlesUseCase
import com.example.newdemo.domain.usecases.MarkAsFavoriteUseCase
import com.example.newdemo.domain.usecases.RemoveFromFavoritesUseCase
import kotlinx.coroutines.launch

class NewsDetailViewModel(
    private val getFavoriteArticlesUseCase: GetFavoriteArticlesUseCase,
    private val markAsFavoriteUseCase: MarkAsFavoriteUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : ViewModel() {
    private val _newsDetailData = MutableLiveData<Article>()
    val newsDetailData: LiveData<Article> get() = _newsDetailData
    private val _favoriteArticles = MutableLiveData<List<Article>>() // Favorite articles
    // Observe favorite articles not use now but if we want to show favorite articles in the future
    val favoriteArticles: LiveData<List<Article>> = _favoriteArticles

    // Get/Set data from bundle
    fun setNewsDetails(newsData: Article) {
        _newsDetailData.value = newsData
    }

    // Fetch only favorite articles
    private fun fetchFavoriteArticles() {
        viewModelScope.launch {
            val favoriteArticles = getFavoriteArticlesUseCase.execute()
            _favoriteArticles.value = favoriteArticles
        }
    }

    // Mark article as favorite
    fun addToFavorites(article: Article) {
        viewModelScope.launch {
            markAsFavoriteUseCase.execute(article)
            fetchFavoriteArticles()
        }
    }

    // Remove article from favorites
    fun removeFromFavorites(article: Article) {
        viewModelScope.launch {
            removeFromFavoritesUseCase.execute(article)
            fetchFavoriteArticles()
        }
    }

}
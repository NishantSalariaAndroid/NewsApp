package com.example.newdemo.domain.usecases

import android.util.Log
import com.example.newdemo.data.models.Article
import com.example.newdemo.data.repository.NewsRepository

class GetFavoriteArticlesUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(): List<Article> {
        Log.d("GetFavoriteArticlesUseCase", newsRepository.getFavoriteArticles().toString())
        return newsRepository.getFavoriteArticles()
    }
}

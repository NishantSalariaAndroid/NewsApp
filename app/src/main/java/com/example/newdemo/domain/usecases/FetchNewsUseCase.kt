package com.example.newdemo.domain.usecases

import com.example.newdemo.data.models.Article
import com.example.newdemo.data.repository.NewsRepository

class FetchNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(): Result<List<Article>> {
        return try {
            val articles = newsRepository.getNewsArticles()
            if (articles.isNullOrEmpty()) {
                Result.failure(Exception("No articles found"))
            } else {
                Result.success(articles)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
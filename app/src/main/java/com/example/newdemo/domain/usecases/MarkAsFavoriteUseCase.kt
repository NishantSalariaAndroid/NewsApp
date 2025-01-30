package com.example.newdemo.domain.usecases

import com.example.newdemo.data.models.Article
import com.example.newdemo.data.repository.NewsRepository


class MarkAsFavoriteUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article) {
        newsRepository.markAsFavorite(article)
    }
}

package com.example.newdemo.data.repository

import android.content.Context
import com.example.newdemo.data.apicalls.NewsApiService
import com.example.newdemo.data.db.ArticleDao
import com.example.newdemo.data.models.Article
import com.example.newdemo.utils.CheckNetwork


class NewsRepository(private val apiService: NewsApiService,private val context: Context,private val articleDao: ArticleDao) {

    suspend fun getNewsArticles(): List<Article>? {
        return if (CheckNetwork.isNetworkAvailable(context)) {
            try {
                val response = apiService.getNewsArticles()
                if (response.isSuccessful) {
                    articleDao.insertArticles(response.body()?.articles!!)
                    response.body()?.articles

                } else {
                    throw Exception("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                throw Exception("Network Error: ${e.message}")
            }
        } else {
            articleDao.getArticles()
        }

    }
    // Method to mark an article as favorite
    suspend fun markAsFavorite(article: Article) {
        article.isFavorite = true
        articleDao.updateArticle(article)
    }
    // Method to remove an article from favorites
    suspend fun removeFromFavorites(article: Article) {
        article.isFavorite = false
        articleDao.updateArticle(article)

    }
    // Method to fetch only favorite articles
    suspend fun getFavoriteArticles(): List<Article> {
        return articleDao.getFavoriteArticles()
    }
}
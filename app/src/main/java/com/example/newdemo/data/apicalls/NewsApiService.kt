package com.example.newdemo.data.apicalls

import com.example.newdemo.data.models.NewsArticle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/everything")
    suspend fun getNewsArticles(
        @Query("q") query: String = "bitcoin",
        @Query("apiKey") apiKey: String = "31a24e13830b40978c4cb93dc2e34133"
    ): Response<NewsArticle>
}
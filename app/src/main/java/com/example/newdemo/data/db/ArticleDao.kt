package com.example.newdemo.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.newdemo.data.models.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<Article>)

    @Query("SELECT * FROM article_table")
    suspend fun getArticles(): List<Article>


    @Update
    suspend fun updateArticle(article: Article): Int

    // Get favorite articles only
    @Query("SELECT * FROM article_table WHERE is_favorite = 1")
    suspend fun getFavoriteArticles(): List<Article>

}
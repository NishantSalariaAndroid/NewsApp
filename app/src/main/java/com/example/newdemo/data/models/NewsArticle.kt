package com.example.newdemo.data.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @Embedded
    val source: Source,

    val author: String?,
    val title: String,
    val description: String?,
    val url: String,

    @ColumnInfo(name = "url_to_image") // Custom column name
    val urlToImage: String?,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false,

    val publishedAt: String,
    val content: String?
)
data class Source(
    @ColumnInfo(name = "source_id")  // Use a custom name for the 'id' field
    val id: String?,

    @ColumnInfo(name = "source_name") // Custom column name for 'name'
    val name: String
)

data class NewsArticle(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)



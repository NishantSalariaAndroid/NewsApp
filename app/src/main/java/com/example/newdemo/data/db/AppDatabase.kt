package com.example.newdemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newdemo.data.models.Article

@Database(entities = [Article::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
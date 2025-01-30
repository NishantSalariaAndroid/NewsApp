package com.example.newdemo.presentation.activities

import android.app.Application
import androidx.room.Room
import com.example.newdemo.data.apicalls.RetrofitInstance
import com.example.newdemo.data.db.AppDatabase
import com.example.newdemo.data.repository.NewsRepository
import com.example.newdemo.domain.usecases.FetchNewsUseCase
import com.example.newdemo.domain.usecases.GetFavoriteArticlesUseCase
import com.example.newdemo.domain.usecases.MarkAsFavoriteUseCase
import com.example.newdemo.domain.usecases.RemoveFromFavoritesUseCase
import com.example.newdemo.presentation.viewmodel.NewListViewModel
import com.example.newdemo.presentation.viewmodel.NewsDetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val appModule = module {
            single { RetrofitInstance.api }
            single { NewsRepository(get(),get(), get()) }
            single { FetchNewsUseCase(get()) }
            single { GetFavoriteArticlesUseCase(get()) }
            single { MarkAsFavoriteUseCase(get()) }
            single { RemoveFromFavoritesUseCase(get()) }
            viewModel { NewListViewModel(get()) }
            viewModel { NewsDetailViewModel(get(), get(),get()) }
            single { Room.databaseBuilder(get(), AppDatabase::class.java, "app_db").build() }
            single { get<AppDatabase>().articleDao() }
        }
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)  // Load Koin module
        }
    }
}
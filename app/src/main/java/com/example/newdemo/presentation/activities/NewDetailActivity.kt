package com.example.newdemo.presentation.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.newdemo.R
import com.example.newdemo.data.models.Article
import com.example.newdemo.databinding.ActivityNewDetailBinding
import com.example.newdemo.presentation.viewmodel.NewsDetailViewModel
import com.example.newdemo.utils.Constants
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewDetailBinding
    private val newsDetailViewModel: NewsDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inItUi()
    }

    // Initialize UI
    private fun inItUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_detail)
        binding.article = newsDetailViewModel
        binding.topBar.topBarTitle.text = getString(R.string.title_news_detail)
        binding.topBar.backButton.visibility = android.view.View.VISIBLE
        getNewsDetailData()
        onBackButtonClicked()
    }

    // Get news detail data from intent
    private fun getNewsDetailData() {
        val articleJson = intent.getStringExtra(Constants.ARTICLE_KEY)
        if (!articleJson.isNullOrEmpty()) {
            val gson = Gson()
            val article = gson.fromJson(articleJson, Article::class.java)
            newsDetailViewModel.setNewsDetails(article)
            Picasso.get()
                .load(article.urlToImage)
                .into(binding.articleImage)
            addToFavorites(article)

        }

    }

    //back button click
    private fun onBackButtonClicked() {
        binding.topBar.backButton.setOnClickListener {
            finish()
        }
    }

    // add to favorites
    private fun addToFavorites(article: Article) {
        binding.favoriteIcon.setOnClickListener {
            if (article.isFavorite) {
                newsDetailViewModel.removeFromFavorites(article)
                binding.favoriteIcon.setImageResource(R.drawable.love)
                Toast.makeText(this, "Remove from favorites successfully", Toast.LENGTH_SHORT)
                    .show()

            } else {
                newsDetailViewModel.addToFavorites(article)
                binding.favoriteIcon.setImageResource(R.drawable.heart)
                Toast.makeText(this, "Add to favorites successfully", Toast.LENGTH_SHORT).show()

            }

        }
    }
}


package com.example.newdemo.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newdemo.R
import com.example.newdemo.data.models.Article
import com.example.newdemo.databinding.ActivityNewsBinding
import com.example.newdemo.presentation.adapter.NewsAdapter
import com.example.newdemo.presentation.clickinterface.ItemClickListener
import com.example.newdemo.presentation.viewmodel.NewListViewModel
import com.example.newdemo.utils.Constants
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewListActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var binding: ActivityNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private val newListViewModel: NewListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inItUi()

    }
// Initialize UI
    private fun inItUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)
        binding.viewModel = newListViewModel
        binding.topBar.topBarTitle.text = getString(R.string.title_news_list)
        getNewsList()
    }

    // Get news list form server
    private fun getNewsList() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(this)
        binding.recyclerView.adapter = newsAdapter

        newListViewModel.newsArticles.observe(this, Observer {
            newsAdapter.submitList(it)
        })
        // Observe error message
        newListViewModel.errorMessage.observe(this, Observer { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
        newListViewModel.showProgressBar.observe(this, Observer {
            if (it) {
                // Show the ProgressBar
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })
    }
   // On item clicked
    override fun onItemClicked(newsArticle: Article, position: Int) {
        val gson = Gson()
        val articleJson = gson.toJson(newsArticle)
        val intent = Intent(this, NewDetailActivity::class.java)
        intent.putExtra(Constants.ARTICLE_KEY, articleJson)
        startActivity(intent)
    }
}

package com.example.newdemo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newdemo.R
import com.example.newdemo.data.models.Article
import com.example.newdemo.databinding.ActivityNewsChildBinding
import com.example.newdemo.presentation.clickinterface.ItemClickListener
import com.squareup.picasso.Picasso

class NewsAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsArticles = mutableListOf<Any>()

    fun submitList(articles: List<Any>) {
        newsArticles.clear()
        newsArticles.addAll(articles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = DataBindingUtil.inflate<ActivityNewsChildBinding>(
            LayoutInflater.from(parent.context),
            R.layout.activity_news_child,
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = newsArticles[position]
        holder.bind(article as Article)
    }

    override fun getItemCount(): Int = newsArticles.size

   inner class NewsViewHolder(private val binding: ActivityNewsChildBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            // Set item click listener
            binding.articleItem.setOnClickListener {
                itemClickListener.onItemClicked(article, adapterPosition)
            }
            Picasso.get()
                .load(article.urlToImage)
                .into(binding.articleImage)
            binding.executePendingBindings()
        }
    }
}

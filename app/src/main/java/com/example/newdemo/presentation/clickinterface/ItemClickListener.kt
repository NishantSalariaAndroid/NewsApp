package com.example.newdemo.presentation.clickinterface

import com.example.newdemo.data.models.Article

interface ItemClickListener {
    fun onItemClicked(newsArticle: Article, position: Int)
}
package com.noox.postschallenge.posts.ui.list

import com.noox.postschallenge.posts.domain.model.Post

interface PostListView {
    fun showLoading()
    fun hideLoading()
    fun showError()
    fun showPosts(posts: List<Post>)
    fun showEmptyList()
}
package com.noox.postschallenge.posts.data

import com.noox.postschallenge.common.data.ApiService

class PostDataSource(private val apiService: ApiService) {

    fun loadPosts() = apiService.loadPosts()

    fun loadComments() = apiService.loadComments()
}